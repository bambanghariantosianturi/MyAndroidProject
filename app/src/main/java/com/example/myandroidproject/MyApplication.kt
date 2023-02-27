package com.example.myandroidproject

import android.app.Application
//import com.example.myandroidproject.core.di.CoreComponent
//import com.example.myandroidproject.core.di.DaggerCoreComponent
//import com.example.myandroidproject.di.AppComponent
//import com.example.myandroidproject.di.DaggerAppComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MyApplication : Application()
//{

//    private val coreComponent: CoreComponent by lazy {
//        DaggerCoreComponent.factory().create(applicationContext)
//    }
//
//    val appComponent: AppComponent by lazy {
//        DaggerAppComponent.factory().create(coreComponent)
//    }
//}