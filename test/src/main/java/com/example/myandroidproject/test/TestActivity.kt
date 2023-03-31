package com.example.myandroidproject.test

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TestActivity : AppCompatActivity() {

    companion object {
        fun startActivity(activity: Activity, bundle: Bundle) {
            activity.startActivity(Intent(activity, TestActivity::class.java).apply {
                putExtras(
                    bundle
                )
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }
}