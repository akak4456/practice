package com.jo.ksppractice

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.jo.intentbuilder_annotation.Extra
import com.jo.intentbuilder_annotation.IntentBuilder

@IntentBuilder
class UserActivity : AppCompatActivity() {

    @Extra
    var name: String? = null

    @Extra
    var age: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UserActivityParser(this).parse()
    }
}