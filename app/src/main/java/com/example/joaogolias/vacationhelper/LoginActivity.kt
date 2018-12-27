package com.example.joaogolias.vacationhelper

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.example.joaogolias.vacationhelper.manager.SharedPreferencesManager
import com.example.joaogolias.vacationhelper.models.Trip
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            this.login()
        }

    }

    fun login(){
        var intent = Intent(this, TripListActivity::class.java)

        val prefsManager = SharedPreferencesManager(this)
        val selectedTrip = prefsManager.getObjectValue("selectedTrip", Trip::class.java)

        if (selectedTrip !== null) {
            intent = Intent(this, HomeActivity::class.java)
        }

        startActivity(intent)
    }
}
