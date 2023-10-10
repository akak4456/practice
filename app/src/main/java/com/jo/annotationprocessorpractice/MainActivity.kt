package com.jo.annotationprocessorpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jo.annotation.CharlesIntent

@CharlesIntent
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Charles.intentForMainActivity(this))
    }
}