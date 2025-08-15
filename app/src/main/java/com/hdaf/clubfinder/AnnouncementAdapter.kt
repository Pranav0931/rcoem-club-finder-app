package com.hdaf.clubfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class AnnouncementAdapter(private var announcements: List<Announcement>) :
    RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder>() {

    class AnnouncementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.announcement_title_text)
        val clubName: TextView = itemView.findViewById(R.id.announcement_club_name_text)
        val description: TextView = itemView.findViewById(R.id.announcement_description_text)
        val timestamp: TextView = itemView.findViewById(R.id.announcement_timestamp_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_announcement, parent, false)
        return AnnouncementViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        val announcement = announcements[position]
        holder.title.text = announcement.title
        holder.clubName.text = "Hosted by ${announcement.clubName}"
        holder.description.text = announcement.description

        // Format the timestamp for display
        announcement.timestamp?.let {
            val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
            holder.timestamp.text = sdf.format(it)
        }
    }

    override fun getItemCount() = announcements.size

    fun updateAnnouncements(newAnnouncements: List<Announcement>) {
        announcements = newAnnouncements
        notifyDataSetChanged()
    }
}
