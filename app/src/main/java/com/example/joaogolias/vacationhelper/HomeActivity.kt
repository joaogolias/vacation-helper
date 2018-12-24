package com.example.joaogolias.vacationhelper

import android.app.Activity
import android.databinding.DataBindingUtil
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.example.joaogolias.vacationhelper.databinding.ActivityHomeBinding
import com.example.joaogolias.vacationhelper.models.Trip
import kotlinx.android.synthetic.main.activity_home.*
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

class HomeActivity : Activity() {
    private var selectedTab: TabItem? = null
    private var trip: Trip? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)

        trip  = Trip(
            "Viagem para Europa",
            1545500812000,
            1545500812000
        )

        setTripStatus()

        binding.trip = trip

        trip_duration_text_view.text = "${trip?.getFormattedDate("initial")} ~ ${trip?.getFormattedDate("final")}"
        
        food_tab.setOnClickListener {tab ->
            println("my click")
            setSelectedTab(tab as TabItem)
        }

        transport_tab.setOnClickListener {tab ->
            setSelectedTab(tab as TabItem)
        }

        hotel_tab.setOnClickListener {tab ->
            setSelectedTab(tab as TabItem)
        }

        other_costs_tab.setOnClickListener {tab ->
            setSelectedTab(tab as TabItem)
        }

        statistic_tab.setOnClickListener {tab ->
            setSelectedTab(tab as TabItem)
        }
    }

    override fun onStart() {
        super.onStart()
        setSelectedTab(food_tab)
    }

    private fun setTripStatus() {
        val tripStausIcon: Drawable? = ContextCompat.getDrawable(this, R.drawable.trip_status_circle)
        tripStausIcon?.let {icon ->
            var iconColorId = R.color.tripNotStartedStatus
            var statusText = getString(R.string.trip_not_started_status)
            trip?.let {
                val currentDateTimestamp: Long = Instant.now().toEpochMilli()

                if (currentDateTimestamp > it.finalDate ) {
                    iconColorId = R.color.tripFinishedStatus
                    statusText = getString(R.string.trip_finished_status)
                } else {
                    if (currentDateTimestamp > it.initialDate) {
                        iconColorId = R.color.tripStartedStatus
                        statusText = getString(R.string.trip_started_status)
                    } else {
                        iconColorId = R.color.tripNotStartedStatus
                        statusText = getString(R.string.trip_not_started_status)
                    }
                }

            }

            val iconColor = ContextCompat.getColor(applicationContext, iconColorId )
            icon.setColorFilter(iconColor, PorterDuff.Mode.SRC_ATOP)
            trip_status_image_view.setImageDrawable(icon)
            trip_status_text_view.text = statusText
            trip_status_text_view.setTextColor(iconColor)
        }

    }

    private fun setSelectedTab(tabItem: TabItem) {
        tabItem.changeColorState()
        selectedTab?.changeColorState()
        selectedTab = tabItem
    }

}
