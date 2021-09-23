package com.makeus.kkongi.rxjava

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.makeus.kkongi.rxjava.databinding.ActivityMainBinding
import io.reactivex.subjects.BehaviorSubject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val subject = BehaviorSubject.createDefault("0")
        binding.ed.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                subject.map { dan -> binding.ed.text.toString().equals("") }
                    .flatMap({ dan -> BehaviorSubject.range(1, 9) }
                    ) { dan, row -> "0 x $row = 0\n" }
                    .scan { x, y -> x + y }
                    .subscribe { text -> binding.tv.text = text }

                subject.map { dan -> binding.ed.text.toString().toLong() }
                    .flatMap({ dan -> BehaviorSubject.range(1, 9) }
                    ) { dan, row -> dan.toString() + " x " + row + " = " + dan * row + "\n" }
                    .scan { x, y -> x + y }
                    .doOnNext { data -> Log.d("onNext()", data) }
                    .subscribe({ text -> binding.tv.text = text}) { obj: Throwable -> obj.message }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }
}