package com.jo.calculatetdd

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jo.calculatetdd.databinding.ActivityMainBinding
import java.util.StringTokenizer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val operation = arrayOf(
        "+",
        "-",
        "×",
        "÷"
    )

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

        binding.buttonPlus.setOnClickListener {
            addOperation(0)
        }

        binding.buttonMinus.setOnClickListener {
            addOperation(1)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun addNumber(number: String) {
        val originInputSplit = binding.tvInput.text.toString().split(*operation)
        Log.e("TMPTEST", originInputSplit.toString())
        var newText = originInputSplit.last() + number
        newText = newText.replace(",", "")
        newText = "%,d".format(newText.toInt())
        binding.tvInput.text =
            originInputSplit.subList(0, originInputSplit.size - 1).joinToString() + newText
    }

    @SuppressLint("SetTextI18n")
    private fun addOperation(operationIdx: Int) {
        binding.tvInput.text = binding.tvInput.text.toString() + operation[operationIdx]
    }
}