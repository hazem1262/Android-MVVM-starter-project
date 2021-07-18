package com.example.mvvmstarterproject.application

import android.app.Application
import com.example.mvvmstarterproject.BuildConfig
import com.example.mvvmstarterproject.utils.ReleaseTree
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(AndroidLogAdapter())

            Timber.plant(object : Timber.DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    Logger.log(priority, tag, message, t)
                }
            })
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}