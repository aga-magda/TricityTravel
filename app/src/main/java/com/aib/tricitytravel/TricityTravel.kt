package com.aib.tricitytravel

import android.app.Activity
import android.app.Application
import android.content.res.Resources
import com.aib.tricitytravel.di.DaggerTricityTravelComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TricityTravel : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        res = resources
        DaggerTricityTravelComponent
            .create()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    companion object {
        var res: Resources? = null
    }
}