package com.example.joaogolias.vacationhelper

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.joaogolias.vacationhelper.adapter.TripListAdapter
import com.example.joaogolias.vacationhelper.models.Trip
import kotlinx.android.synthetic.main.activity_home.*

import kotlinx.android.synthetic.main.activity_trip_list.*

class TripListActivity : AppCompatActivity() {
    private lateinit var trips: List<Trip>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_list)
        setSupportActionBar(trip_list_toolbar)

        getTrips()
        trips_list.adapter = TripListAdapter(trips, this)
        trips_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var intent: Intent
        when (item?.itemId) {
            R.id.logout -> {
                intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu);
        menu?.findItem(R.id.edit_trip)?.isVisible = false
        menu?.findItem(R.id.other_trips)?.isVisible = false
        return true
    }

    fun getTrips() {
        trips = listOf(
            Trip(
                "Viagem para Europa 1",
                1545500812000,
                1545500812000
            ),Trip(
                "Viagem para Europa 2",
                1545500812000,
                1545500812000
            )
        )
    }

}
