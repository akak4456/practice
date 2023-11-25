package com.example.practice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    fun test(onDone: () -> Unit) {
        viewModelScope.launch {
            onDone()
            delay(1000L)
            onDone()
        }
    }
}