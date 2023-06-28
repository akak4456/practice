package com.example.wheelpickerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aigestudio.wheelpicker.WheelPicker

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<WheelPicker>(R.id.wheel_picker).apply { 
            data = (0..99).map { 
                "$it 번째"
            }
            isCurved = true
            setDebug(true)
        }
    }
}