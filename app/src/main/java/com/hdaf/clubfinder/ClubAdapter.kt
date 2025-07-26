package com.hdaf.clubfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// The constructor now accepts a lambda function for handling clicks
class ClubAdapter(
    private var clubs: List<Club>,
    private val onItemClicked: (Club) -> Unit
) : RecyclerView.Adapter<ClubAdapter.ClubViewHolder>() {

    // ViewHolder holds the views for each item in the list
    class ClubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: TextView = itemView.findViewById(R.id.item_logo)
        val name: TextView = itemView.findViewById(R.id.item_name)
        val description: TextView = itemView.findViewById(R.id.item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_club, parent, false)
        return ClubViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        val club = clubs[position]
        holder.logo.text = club.logo
        holder.name.text = club.name
        // Use the shorter description for the list item
        holder.description.text = club.description

        // Use the passed-in lambda for the click listener
        holder.itemView.setOnClickListener {
            onItemClicked(club)
        }
    }

    override fun getItemCount() = clubs.size

    // Function to update the list of clubs when filtering
    fun updateClubs(newClubs: List<Club>) {
        clubs = newClubs
        notifyDataSetChanged()
    }
}
