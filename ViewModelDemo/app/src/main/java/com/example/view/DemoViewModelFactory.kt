package com.example.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DemoViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DemoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DemoViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}