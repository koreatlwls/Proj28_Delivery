package com.example.proj28_delivery.di

import android.app.Activity
import com.example.proj28_delivery.BuildConfig
import com.example.proj28_delivery.data.api.SweetTrackerApi
import com.example.proj28_delivery.data.api.Url
import com.example.proj28_delivery.data.db.AppDatabase
import com.example.proj28_delivery.data.entity.TrackingInformation
import com.example.proj28_delivery.data.entity.TrackingItem
import com.example.proj28_delivery.data.preference.PreferenceManager
import com.example.proj28_delivery.data.preference.SharedPreferenceManager
import com.example.proj28_delivery.data.repository.ShippingCompanyRepository
import com.example.proj28_delivery.data.repository.ShippingCompanyRepositoryImpl
import com.example.proj28_delivery.data.repository.TrackingItemRepository
import com.example.proj28_delivery.data.repository.TrackingItemRepositoryImpl
import com.example.proj28_delivery.presentation.addtrackingitem.AddTrackingItemFragment
import com.example.proj28_delivery.presentation.addtrackingitem.AddTrackingItemPresenter
import com.example.proj28_delivery.presentation.addtrackingitem.AddTrackingItemsContract
import com.example.proj28_delivery.presentation.trackinghistory.TrackingHistoryContract
import com.example.proj28_delivery.presentation.trackinghistory.TrackingHistoryFragment
import com.example.proj28_delivery.presentation.trackinghistory.TrackingHistoryPresenter
import com.example.proj28_delivery.presentation.trackingitems.TrackingItemsContract
import com.example.proj28_delivery.presentation.trackingitems.TrackingItemsFragment
import com.example.proj28_delivery.presentation.trackingitems.TrackingItemsPresenter
import com.example.proj28_delivery.work.AppWorkerFactory
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {

    single { Dispatchers.IO }

    // Database
    single { AppDatabase.build(androidApplication()) }
    single { get<AppDatabase>().trackingItemDao() }
    single { get<AppDatabase>().shippingCompanyDao() }

    // Api
    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()
    }
    single<SweetTrackerApi> {
        Retrofit.Builder().baseUrl(Url.SWEET_TRACKER_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create()
    }

    // Preference
    single { androidContext().getSharedPreferences("preference", Activity.MODE_PRIVATE) }
    single<PreferenceManager> { SharedPreferenceManager(get()) }

    // Repository
//    single<TrackingItemRepository> { TrackingItemRepositoryStub() }
    single<TrackingItemRepository> { TrackingItemRepositoryImpl(get(), get(), get()) }
    single<ShippingCompanyRepository> { ShippingCompanyRepositoryImpl(get(), get(), get(), get()) }

    // Work
    single { AppWorkerFactory(get(), get()) }

    // Presentation
    scope<TrackingItemsFragment> {
        scoped<TrackingItemsContract.Presenter> { TrackingItemsPresenter(getSource(), get()) }
    }
    scope<AddTrackingItemFragment> {
        scoped<AddTrackingItemsContract.Presenter> {
            AddTrackingItemPresenter(getSource(), get(), get())
        }
    }
    scope<TrackingHistoryFragment> {
        scoped<TrackingHistoryContract.Presenter> { (trackingItem: TrackingItem, trackingInformation: TrackingInformation) ->
            TrackingHistoryPresenter(getSource(), get(), trackingItem, trackingInformation)
        }
    }
}