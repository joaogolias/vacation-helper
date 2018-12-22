package com.example.joaogolias.vacationhelper

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_button.setOnClickListener {
            this.login();
        }
    }

    fun login(){
        var intent = Intent(this, HomeActivity::class.java);
        startActivity(intent);
    }
}
