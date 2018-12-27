package com.example.joaogolias.vacationhelper.adapter

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.joaogolias.vacationhelper.HomeActivity
import com.example.joaogolias.vacationhelper.R
import com.example.joaogolias.vacationhelper.manager.SharedPreferencesManager
import com.example.joaogolias.vacationhelper.models.Trip
import kotlinx.android.synthetic.main.trip_list_item.view.*
import java.time.Instant

class TripListAdapter(private val tripList: List<Trip>,
                      private val appCompatActivity: AppCompatActivity) : RecyclerView.Adapter<TripListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(appCompatActivity).inflate(R.layout.trip_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val trip = tripList[position]

        holder?.let {
            it.containerView.setOnClickListener {
                val prefsManager = SharedPreferencesManager(appCompatActivity)
                prefsManager.saveObjectValue(trip, "selectedTrip")

                val intent = Intent(appCompatActivity, HomeActivity::class.java)
                appCompatActivity.startActivity(intent)
            }

            it.titleView.text = trip.name
            it.durationView.text = "${trip?.getFormattedDate("initial")} ~ ${trip?.getFormattedDate("final")}"
            setTripStatus(trip, it.tripStatusTextView, it.tripStatusImageView)
        }
    }

    override fun getItemCount(): Int {
        return tripList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView = itemView.trip_item_title
        val durationView = itemView.trip_item_duration
        val tripStatusImageView = itemView.trip_item_status_image_view
        val tripStatusTextView = itemView.trip_item_status_text_view
        val containerView = itemView.trip_item_container
    }

    private fun setTripStatus(trip: Trip, textView: TextView, imgView: ImageView) {
        val tripStausIcon: Drawable? = ContextCompat.getDrawable(appCompatActivity, R.drawable.trip_status_circle)
        tripStausIcon?.let {icon ->
            var iconColorId = R.color.tripNotStartedStatus
            var statusText = appCompatActivity.getString(R.string.trip_not_started_status)
            trip?.let {
                val currentDateTimestamp: Long = Instant.now().toEpochMilli()

                if (currentDateTimestamp > it.finalDate ) {
                    iconColorId = R.color.tripFinishedStatus
                    statusText = appCompatActivity.getString(R.string.trip_finished_status)
                } else {
                    if (currentDateTimestamp > it.initialDate) {
                        iconColorId = R.color.tripStartedStatus
                        statusText = appCompatActivity.getString(R.string.trip_started_status)
                    } else {
                        iconColorId = R.color.tripNotStartedStatus
                        statusText = appCompatActivity.getString(R.string.trip_not_started_status)
                    }
                }

            }

            val iconColor = ContextCompat.getColor(appCompatActivity.applicationContext, iconColorId )
            icon.setColorFilter(iconColor, PorterDuff.Mode.SRC_ATOP)
            imgView.setImageDrawable(icon)
            textView.text = statusText
            textView.setTextColor(iconColor)
        }

    }

}