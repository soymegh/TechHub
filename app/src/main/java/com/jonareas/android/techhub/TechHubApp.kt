package com.jonareas.android.techhub

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TechHubApp : Application() {

    companion object {
        lateinit var INSTANCE : Application private set
        val applicationContext : Context by lazy { INSTANCE.applicationContext }
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

}