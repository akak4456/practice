package com.example.flowdemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

class DemoViewModel : ViewModel() {
    val myFlow: Flow<Int> = flow {
        for(i in 0..9) {
            emit(i)
            delay(2000)
        }
    }

    val newFlow = myFlow.map {
        "Current Value = $it"
    }
}