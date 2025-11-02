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

/**
 * EventsFragment displays a calendar of all college events.
 * Dates with events are highlighted. Clicking a date shows event details.
 */
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

    /**
     * Observes LiveData for calendar event dots and updates the calendar.
     */
    private fun observeViewModel() {
        viewModel.calendarEventDots.observe(viewLifecycleOwner) { eventDays ->
            calendarView.setEvents(eventDays)
        }
    }

    /**
     * Sets up listener for calendar day clicks.
     * Shows event details for the selected day in a bottom sheet.
     */
    private fun setupCalendarListener() {
        calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: com.applandeo.materialcalendarview.EventDay) {
                val clickedDate = getNormalizedDate(eventDay.calendar.time)
                val eventsForDay = viewModel.eventsByDate.value?.get(clickedDate) ?: emptyList()

                if (eventsForDay.isNotEmpty()) {
                    val bottomSheet = DayDetailsBottomSheetFragment.newInstance(eventDay.calendar, eventsForDay)
                    bottomSheet.show(childFragmentManager, bottomSheet.tag)
                }
            }
        })
    }

    /**
     * Helper function to normalize a Date for consistent comparisons.
     */
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