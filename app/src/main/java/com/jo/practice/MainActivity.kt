package com.jo.practice

import android.app.ActivityManager
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import com.jo.practice.Extensions.showSimpleToast

class MainActivity : AppCompatActivity() {

    private val messengerIPCHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MyMessengerIPCService.MSG_ADD_RESPONSE -> showSimpleToast("Add response: ${msg.arg1}")
                else -> super.handleMessage(msg)
            }
        }
    }
    private val messengerIPCClient = Messenger(messengerIPCHandler)
    private var messengerIPCService: Messenger? = null
    private val messengerIPCServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            messengerIPCService = Messenger(service).apply {
                send(Message.obtain(null, MyMessengerIPCService.MSG_BIND_CLIENT, 0, 0).apply {
                    replyTo = messengerIPCClient
                })
            }
            showSimpleToast("MessengerIPCService - onServiceConnected")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            messengerIPCService = null
            showSimpleToast("MessengerIPCService - onServiceDisconnected")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Intent(this, MyForegroundService::class.java).run {
//            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) startForegroundService(this)
//            else startService(this)
//        }

        findViewById<Button>(R.id.btn_bind).setOnClickListener {
            bindMessengerService()
        }

        findViewById<Button>(R.id.btn_unbind).setOnClickListener {
            unbindMessengerService()
        }
        findViewById<Button>(R.id.btn_show_toast).setOnClickListener {
            showMessengerIPCServiceToast()
        }
    }

    private fun bindMessengerService() {
        Intent(this, MyMessengerIPCService::class.java).run {
            bindService(this, messengerIPCServiceConnection, Service.BIND_AUTO_CREATE)
        }
    }

    private fun unbindMessengerService() {
        messengerIPCService?.send(Message.obtain(null, MyMessengerIPCService.MSG_UNBIND_CLIENT, 0, 0).apply {
            replyTo = messengerIPCClient
        })
        unbindService(messengerIPCServiceConnection)
    }

    private fun showMessengerIPCServiceToast() {
        messengerIPCService?.send(Message.obtain(null, MyMessengerIPCService.MSG_SHOW_TOAST, 0, 0).apply {
            data = bundleOf(MyMessengerIPCService.MSG_TOAST_TEXT to "Messenger IPC Service!")
        })
    }

    private fun invokeAddMessengerIPCService() {
        messengerIPCService?.send(Message.obtain(null, MyMessengerIPCService.MSG_ADD_REQUEST, 5, 1))
    }
}