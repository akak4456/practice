package com.example.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    val vm: ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(FragmentA())
    }

    fun replaceFragment(fragment: BaseFragment<*>) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }
}