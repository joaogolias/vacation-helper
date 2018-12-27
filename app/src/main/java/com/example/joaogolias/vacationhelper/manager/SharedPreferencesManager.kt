package com.example.joaogolias.vacationhelper.manager

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import com.google.gson.Gson


class SharedPreferencesManager(private var contextWrapper: ContextWrapper) {
    private var prefs: SharedPreferences
    private var gson: Gson = Gson()
    val PREFS_FILENAME = "com.example.joaogolias.vacationhelper.prefs"

    init {
        prefs = this.contextWrapper.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    }

    fun saveObjectValue(obj: Any, key: String) {
        val json = gson.toJson(obj)
        val preferencesEditor = prefs.edit()
        preferencesEditor.putString(key, json)
        preferencesEditor.apply()
    }

    fun <T>getObjectValue(key: String, classToConvert: Class<T>): T? {
        val jsonObject = prefs.getString(key, "")
        println("jsonObject: $jsonObject")
        if (jsonObject == "") {
            return null
        }

        return gson.fromJson<T>(jsonObject, classToConvert)
    }
}