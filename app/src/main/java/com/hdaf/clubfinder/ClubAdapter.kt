package com.hdaf.clubfinder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip

// The adapter now takes a mutable list and a lambda function for click events
class ClubAdapter(
    private var clubs: MutableList<Club>,
    private val onItemClicked: (Club) -> Unit
) : RecyclerView.Adapter<ClubAdapter.ClubViewHolder>() {

    class ClubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: TextView = itemView.findViewById(R.id.club_logo)
        val name: TextView = itemView.findViewById(R.id.club_name)
        val description: TextView = itemView.findViewById(R.id.club_description)
        val interestChip: Chip = itemView.findViewById(R.id.chip_interest)
        val departmentChip: Chip = itemView.findViewById(R.id.chip_department)

        // Bind the click listener to the item view
        fun bind(club: Club, onItemClicked: (Club) -> Unit) {
            itemView.setOnClickListener { onItemClicked(club) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_club, parent, false)
        return ClubViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        val club = clubs[position]
        holder.logo.text = club.logo
        holder.name.text = club.name
        holder.description.text = club.description
        holder.interestChip.text = club.interest
        holder.departmentChip.text = club.department
        // Call the bind function to set the click listener
        holder.bind(club, onItemClicked)
    }

    override fun getItemCount() = clubs.size

    // Method to update the list with filtered results
    @SuppressLint("NotifyDataSetChanged")
    fun updateClubs(filteredClubs: List<Club>) {
        clubs.clear()
        clubs.addAll(filteredClubs)
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
}
