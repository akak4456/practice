package com.jo.practice

import android.app.IntentService
import android.content.Intent

class RSSPullService : IntentService(RSSPullService::class.simpleName) {
    override fun onHandleIntent(intent: Intent?) {
        val dataString = intent?.dataString
    }
}