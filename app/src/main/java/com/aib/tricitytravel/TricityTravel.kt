package com.aib.tricitytravel

import android.app.Application
import android.content.res.Resources

class TricityTravel : Application() {

    override fun onCreate() {
        super.onCreate()
        res = resources
    }

    companion object {
        var res: Resources? = null
    }
}