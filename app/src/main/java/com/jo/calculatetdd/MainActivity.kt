package com.jo.calculatetdd

import android.annotation.SuppressLint
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
            addNumber("1")
        }
        findViewById<TextView>(R.id.button_no_2).setOnClickListener {
            addNumber("2")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun addNumber(number: String) {
        val tv = findViewById<TextView>(R.id.tv_input)
        tv.text = tv.text.toString() + number
    }
}