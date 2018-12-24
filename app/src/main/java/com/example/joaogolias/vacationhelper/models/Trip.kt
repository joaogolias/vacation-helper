package com.example.joaogolias.vacationhelper.models

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

class Trip(
    var name: String,
    var initialDate: Long,
    var finalDate: Long
) {

    fun getFormattedDate(type: String): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val date: Date = when(type) {
            ("initial") -> Date(initialDate)
            else -> Date(finalDate)
        }
        return sdf.format(date)
    }

}