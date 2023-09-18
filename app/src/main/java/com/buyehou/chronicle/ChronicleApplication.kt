package com.buyehou.chronicle

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.google.android.material.color.DynamicColors

/**
 * @author Rosen
 * @date 2023/9/6 12:18
 */
class ChronicleApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        context = applicationContext
    }

}