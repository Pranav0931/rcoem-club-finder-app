package com.hdaf.clubfinder

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DayEventsAdapter(private val events: List<Event>) : RecyclerView.Adapter<DayEventsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val indicator: View = itemView.findViewById(R.id.event_type_indicator)
        val title: TextView = itemView.findViewById(R.id.day_event_title)
        val club: TextView = itemView.findViewById(R.id.day_event_club)
        val timeAndVenue: TextView = itemView.findViewById(R.id.day_event_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_day_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]
        holder.title.text = event.eventName
        holder.club.text = "By ${event.clubName}"
        holder.timeAndVenue.text = "${event.time} at ${event.venue}"

        // Set the indicator color based on event type
        val color = when (event.eventType) {
            "Interview" -> "#0F9D58" // Green
            "Announcement" -> "#4285F4" // Blue
            else -> "#FFA500" // Orange for Activity
        }
        holder.indicator.setBackgroundColor(Color.parseColor(color))
    }

    override fun getItemCount() = events.size
}
