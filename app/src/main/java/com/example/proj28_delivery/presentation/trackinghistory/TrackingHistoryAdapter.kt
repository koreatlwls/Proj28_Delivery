package com.example.proj28_delivery.presentation.trackinghistory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proj28_delivery.data.entity.TrackingDetail
import com.example.proj28_delivery.databinding.ItemTrackingHistoryBinding

class TrackingHistoryAdapter : RecyclerView.Adapter<TrackingHistoryAdapter.ViewHolder>() {

    var data: List<TrackingDetail> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemTrackingHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(private val binding: ItemTrackingHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(info: TrackingDetail) {
            binding.timeStampTextView.text = info.timeString
            binding.stateTextView.text = info.kind
            binding.locationTextView.text = "@${info.where}"
        }
    }
}