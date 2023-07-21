package com.telkom.antares.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.telkom.antares.data.dao.tracker.TrackerData
import com.telkom.antares.databinding.TrackerListBinding

class TrackerAdapter(private var onClick : TrackerAdapter.TrackersInterface) : RecyclerView.Adapter<TrackerAdapter.ViewHolder>() {

    private var diffCallback = object : DiffUtil.ItemCallback<TrackerData>(){

        override fun areItemsTheSame(oldItem: TrackerData, newItem: TrackerData): Boolean {
            return oldItem.id_tracker == newItem.id_tracker
        }

        override fun areContentsTheSame(oldItem: TrackerData, newItem: TrackerData): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    inner class ViewHolder (private val binding: TrackerListBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(Trackers: TrackerData){
            binding.apply {
                dataTracker = Trackers

                btnNext.setOnClickListener {
                    onClick.detailTracker(Trackers)
                }
            }
        }
    }

    interface TrackersInterface {
        fun detailTracker(Trackers: TrackerData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = TrackerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    fun setData(data : List<TrackerData>){
        differ.submitList(data)
    }
}