package com.music.myartistsapplication

import android.app.Application
import com.music.myartistsapplication.di.ApplicationComponent
import com.music.myartistsapplication.di.DaggerApplicationComponent

class ArtistsApplication : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}