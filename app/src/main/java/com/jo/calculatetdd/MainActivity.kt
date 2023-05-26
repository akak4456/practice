package com.jo.calculatetdd

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.jo.calculatetdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        listOf(
            binding.buttonNo1,
            binding.buttonNo2,
            binding.buttonNo3,
            binding.buttonNo4,
            binding.buttonNo5,
            binding.buttonNo6,
            binding.buttonNo7,
            binding.buttonNo8,
            binding.buttonNo9,
            binding.buttonNo0,
        ).forEach { tv ->
            tv.setOnClickListener {
              addNumber(tv.text.toString())
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun addNumber(number: String) {
        val tv = findViewById<TextView>(R.id.tv_input)
        var newText = tv.text.toString() + number
        newText = newText.replace(",", "")
        newText = "%,d".format(newText.toInt())
        tv.text = newText
    }
}