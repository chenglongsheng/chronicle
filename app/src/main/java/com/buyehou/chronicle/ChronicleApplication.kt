package com.buyehou.chronicle

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

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
        context = applicationContext
    }

}