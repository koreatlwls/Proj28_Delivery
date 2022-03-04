package com.example.proj28_delivery.presentation.trackingitems

import com.example.proj28_delivery.data.entity.TrackingInformation
import com.example.proj28_delivery.data.entity.TrackingItem
import com.example.proj28_delivery.presentation.BasePresenter
import com.example.proj28_delivery.presentation.BaseView

class TrackingItemsContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showNoDataDescription()

        fun showTrackingItemInformation(trackingItemInformation: List<Pair<TrackingItem, TrackingInformation>>)
    }

    interface Presenter: BasePresenter{

        var trackingItemInformation: List<Pair<TrackingItem, TrackingInformation>>

        fun refresh()
    }
}