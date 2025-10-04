package com.hdaf.clubfinder.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applandeo.materialcalendarview.EventDay
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.hdaf.clubfinder.Event
import com.hdaf.clubfinder.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class EventsViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _eventsByDate = MutableLiveData<Map<Date, List<Event>>>()
    val eventsByDate: LiveData<Map<Date, List<Event>>> = _eventsByDate

    private val _calendarEventDots = MutableLiveData<List<EventDay>>()
    val calendarEventDots: LiveData<List<EventDay>> = _calendarEventDots

    private val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    init {
        fetchEvents()
    }

    private fun fetchEvents() {
        db.collection("events")
            .orderBy("date")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("EventsViewModel", "Listen failed.", e)
                    return@addSnapshotListener
                }
                if (snapshots != null) {
                    processSnapshots(snapshots)
                }
            }
    }

    private fun processSnapshots(snapshots: QuerySnapshot) {
        val newEventsByDate = mutableMapOf<Date, MutableList<Event>>()
        val allFetchedEvents = mutableListOf<Event>()

        for (doc in snapshots) {
            try {
                val event = doc.toObject(Event::class.java)
                allFetchedEvents.add(event)

                val date = sdf.parse(event.date)
                if (date != null) {
                    val normalizedDate = getNormalizedDate(date)
                    newEventsByDate.getOrPut(normalizedDate) { mutableListOf() }.add(event)
                }
            } catch (ex: Exception) {
                Log.e("EventsViewModel", "Error parsing date or event: ${doc.id}", ex)
            }
        }

        _eventsByDate.value = newEventsByDate
        updateCalendarDots(allFetchedEvents)
    }

    private fun updateCalendarDots(allEvents: List<Event>) {
        val eventDays = mutableListOf<EventDay>()

        allEvents.forEach { event ->
            try {
                val date = sdf.parse(event.date)
                if (date != null) {
                    val calendar = Calendar.getInstance()
                    calendar.time = date

                    val dotDrawable = when (event.eventType) {
                        "Interview" -> R.drawable.dot_interview
                        "Announcement" -> R.drawable.dot_announcement
                        else -> R.drawable.dot_activity
                    }

                    eventDays.add(EventDay(calendar, dotDrawable))
                }
            } catch (e: Exception) {
                Log.e("EventsViewModel", "Error creating event dot for date: ${event.date}", e)
            }
        }

        // Avoid duplicate dots for the same date
        _calendarEventDots.value = eventDays.distinctBy { getNormalizedDate(it.calendar.time) }
    }

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

