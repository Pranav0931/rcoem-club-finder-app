package com.hdaf.clubfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class EventAdapter(private var events: List<Event>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventName: TextView = itemView.findViewById(R.id.event_name_text)
        val clubName: TextView = itemView.findViewById(R.id.event_club_name_text)
        val date: TextView = itemView.findViewById(R.id.event_date_text)
        val time: TextView = itemView.findViewById(R.id.event_time_text)
        val venue: TextView = itemView.findViewById(R.id.event_venue_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.eventName.text = event.eventName
        holder.clubName.text = "Hosted by ${event.clubName}"
        holder.date.text = event.date
        holder.time.text = event.time
        holder.venue.text = event.venue
    }

    override fun getItemCount() = events.size

    fun updateEvents(newEvents: List<Event>) {
        events = newEvents
        notifyDataSetChanged()
    }
}
