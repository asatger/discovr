package org.discovr.mobile

import android.app.Application
import org.discovr.mobile.core.di.KoinApp

class DiscovrApp : Application() {

    override fun onCreate() {
        super.onCreate()
        this.initializeDI()
    }

    private fun initializeDI() {
        KoinApp.start(this)
    }
}