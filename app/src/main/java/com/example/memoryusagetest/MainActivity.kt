package com.example.memoryusagetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity  : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        supportFragmentManager.beginTransaction().add(R.id.frameLayout, HomeFragment()).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.page_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout , HomeFragment()).commitAllowingStateLoss()
                return true
            }
            R.id.page_tv -> {
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, TVFragment()).commitAllowingStateLoss()
                return true
            }
            R.id.page_calendar -> {
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, CalendarFragment()).commitAllowingStateLoss()
                return true
            }
        }

        return false
    }
}