package com.kudos.roominfocus.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kudos.roominfocus.databinding.RowItemScheduleBinding
import com.kudos.roominfocus.db.entity.Schedule
import java.text.SimpleDateFormat
import java.util.*

class ScheduleAdapter(private val onItemClick: (Schedule) -> Unit) :
    ListAdapter<Schedule, ScheduleAdapter.ViewHolder>(callback) {

    companion object {
        val callback = object : DiffUtil.ItemCallback<Schedule>() {
            override fun areItemsTheSame(
                oldItem: Schedule,
                newItem: Schedule
            ) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Schedule,
                newItem: Schedule
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(val rowItemScheduleBinding: RowItemScheduleBinding) :
        RecyclerView.ViewHolder(rowItemScheduleBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        RowItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            rowItemScheduleBinding.apply {
                val item = getItem(position)
                if(position==0){
                    stopNameLabel.visibility = View.VISIBLE
                    arrivalTimeLabel.visibility = View.VISIBLE
                }
                stopNameTextView.text = item.stopName
                arrivalTimeTextView.text = SimpleDateFormat(
                    "hh:mm a", Locale.getDefault()
                ).format(
                    Date(item.arrivalTime.toLong() * 1000)
                )
                itemView.setOnClickListener {
                    onItemClick(item)
                }

            }
        }
    }

}