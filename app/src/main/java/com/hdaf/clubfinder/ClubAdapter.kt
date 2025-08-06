package com.hdaf.clubfinder

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

// The constructor accepts a list of clubs and a lambda function for handling clicks
class ClubAdapter(
    private var clubs: List<Club>,
    private val onItemClicked: (Club) -> Unit
) : RecyclerView.Adapter<ClubAdapter.ClubViewHolder>() {

    class ClubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Get references to the views in the list item layout
        private val logoCardView: CardView = itemView.findViewById(R.id.logo_card_view)
        private val logo: TextView = itemView.findViewById(R.id.item_logo)
        private val name: TextView = itemView.findViewById(R.id.item_name)
        private val description: TextView = itemView.findViewById(R.id.item_description)

        // Bind data to the views and set the click listener for an item
        fun bind(club: Club, onItemClicked: (Club) -> Unit) {
            logo.text = club.logo
            name.text = club.name
            description.text = club.description

            // Set the background color with a fallback for safety
            try {
                logoCardView.setCardBackgroundColor(Color.parseColor(club.colorHex))
            } catch (e: IllegalArgumentException) {
                logoCardView.setCardBackgroundColor(Color.LTGRAY)
            }

            // Set the click listener for the entire item view
            itemView.setOnClickListener {
                onItemClicked(club)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_club, parent, false)
        return ClubViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        val club = clubs[position]
        holder.bind(club, onItemClicked)
    }

    override fun getItemCount() = clubs.size

    // Function to update the adapter's data and refresh the list
    fun updateClubs(newClubs: List<Club>) {
        clubs = newClubs
        notifyDataSetChanged()
    }
}
