package com.example.proj28_delivery.presentation.trackinghistory

import com.example.proj28_delivery.data.entity.TrackingInformation
import com.example.proj28_delivery.data.entity.TrackingItem
import com.example.proj28_delivery.presentation.BasePresenter
import com.example.proj28_delivery.presentation.BaseView

class TrackingHistoryContract {

    interface View : BaseView<Presenter> {

        fun hideLoadingIndicator()

        fun showTrackingItemInformation(trackingItem: TrackingItem, trackingInformation: TrackingInformation)

        fun finish()
    }

    interface Presenter : BasePresenter {

        fun refresh()

        fun deleteTrackingItem()
    }
}