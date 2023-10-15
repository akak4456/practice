package com.jo.practice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast

class MyBinderExtendedService : Service() {
    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onBind(intent: Intent): IBinder {
        return MyBinder()
    }

    inner class MyBinder : Binder() {
        val service: MyBinderExtendedService
            get() = this@MyBinderExtendedService
    }
}