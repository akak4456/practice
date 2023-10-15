package com.jo.practice

import android.app.Application
import android.widget.TextView

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appTextView = TextView(applicationContext).apply {
            text = "ABCDEF"
        }
    }

    companion object {
        lateinit var appTextView: TextView
    }
}