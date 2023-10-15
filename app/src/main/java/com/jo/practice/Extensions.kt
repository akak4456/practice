package com.jo.practice

import android.content.Context
import android.widget.Toast

object Extensions {
    fun Context.showSimpleToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}