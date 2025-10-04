package com.hdaf.clubfinder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hdaf.clubfinder.DayEventsAdapter
import com.hdaf.clubfinder.Event
import com.hdaf.clubfinder.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DayDetailsBottomSheetFragment : BottomSheetDialogFragment() {

    private var events: List<Event> = emptyList()
    private var selectedDate: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve the events and date passed from the EventsFragment
        arguments?.let {
            events = it.getSerializable("EVENTS") as? List<Event> ?: emptyList()
            selectedDate = it.getSerializable("DATE") as? Calendar ?: Calendar.getInstance()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_day_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dateTextView = view.findViewById<TextView>(R.id.day_details_date)
        val recyclerView = view.findViewById<RecyclerView>(R.id.day_details_recycler_view)

        // Format and set the date title
        val dateFormat = SimpleDateFormat("MMMM d", Locale.getDefault())
        dateTextView.text = "Events for ${dateFormat.format(selectedDate.time)}"

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = DayEventsAdapter(events)
    }

    companion object {
        fun newInstance(date: Calendar, events: List<Event>): DayDetailsBottomSheetFragment {
            val fragment = DayDetailsBottomSheetFragment()
            val args = Bundle()
            args.putSerializable("DATE", date)
            args.putSerializable("EVENTS", ArrayList(events)) // ArrayList is Serializable
            fragment.arguments = args
            return fragment
        }
    }
}
