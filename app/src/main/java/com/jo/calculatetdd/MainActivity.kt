package com.jo.calculatetdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        findViewById<TextView>(R.id.button_no_1).setOnClickListener {
            findViewById<TextView>(R.id.tv_input).text = "1"
        }
    }
}