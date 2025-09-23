package com.hdaf.clubfinder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.hdaf.clubfinder.R
import java.util.Calendar
import java.util.Date

class EventsFragment : Fragment() {

    private lateinit var calendarView: CalendarView
    private val viewModel: EventsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_events, container, false)
        calendarView = view.findViewById(R.id.calendarView)

        setupCalendarListener()
        observeViewModel()

        return view
    }

    private fun observeViewModel() {
        // Observes the LiveData from the ViewModel and updates the calendar dots
        viewModel.calendarEventDots.observe(viewLifecycleOwner) { eventDays ->
            calendarView.setEvents(eventDays)
        }
    }

    private fun setupCalendarListener() {
        // This explicit listener resolves the type mismatch error
        calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: com.applandeo.materialcalendarview.EventDay) {
                val clickedDate = getNormalizedDate(eventDay.calendar.time)
                // Safely gets the events for the clicked date from the ViewModel
                val eventsForDay = viewModel.eventsByDate.value?.get(clickedDate) ?: emptyList()

                if (eventsForDay.isNotEmpty()) {
                    // Creates and shows the bottom sheet with the event details
                    val bottomSheet = DayDetailsBottomSheetFragment.newInstance(eventDay.calendar, eventsForDay)
                    bottomSheet.show(childFragmentManager, bottomSheet.tag)
                }
            }
        })
    }

    // Helper function to normalize a Date for consistent comparisons
    private fun getNormalizedDate(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }
}

