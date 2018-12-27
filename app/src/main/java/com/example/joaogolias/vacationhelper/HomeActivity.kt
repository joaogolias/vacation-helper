package com.example.joaogolias.vacationhelper

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import com.example.joaogolias.vacationhelper.databinding.ActivityHomeBinding
import com.example.joaogolias.vacationhelper.models.Trip
import kotlinx.android.synthetic.main.activity_home.*
import java.time.Instant
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.joaogolias.vacationhelper.manager.SharedPreferencesManager


class HomeActivity : AppCompatActivity() {
    private var selectedTab: TabItem? = null
    private var trip: Trip? = null
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)

        setTrip()

        setTripStatus()

        setToolbarConfig()

        trip_duration_text_view.text = "${trip?.getFormattedDate("initial")} ~ ${trip?.getFormattedDate("final")}"

        setTabItemsClick()
    }

    override fun onStart() {
        super.onStart()
        setSelectedTab(food_tab)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.edit_trip ->  {
                return true
            }
            R.id.other_trips -> {
                return true
            }
            R.id.logout -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun setTrip() {
        trip  = Trip(
            "Viagem para Europa",
            1545500812000,
            1545500812000
        )

        trip?.let {
            val prefsManager = SharedPreferencesManager(this)
            println("saving trip")
            println("trip: $trip")
            prefsManager.saveObjectValue(it, "selectedTrip")
        }

        binding.trip = trip
    }

    private fun setToolbarConfig() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setTabItemsClick() {
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
