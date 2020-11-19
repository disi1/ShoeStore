package com.udacity.shoestore

import android.app.Application

class ShoeStoreApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ShoeStoreAppPreferences.init(this)
    }
}