package com.ovais.android_quick_start.core.initializers

import android.content.Context
import android.os.StrictMode
import androidx.startup.Initializer
import com.ovais.android_quick_start.BuildConfig

class StrictModeInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        enableStrictMode()
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> = emptyList()
    private fun enableStrictMode() {
        if (BuildConfig.DEBUG) {
            // Thread policy: catch disk/network/violations on main thread
            val threadPolicy = StrictMode.ThreadPolicy.Builder()
                .detectAll() // detects disk reads/writes, network, custom slow calls
                .penaltyLog() // logs to logcat
                .penaltyDialog() // optional: show a dialog on violation
                .build()

            // VM policy: catch leaks and resource issues
            val vmPolicy = StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .detectActivityLeaks()
                .detectLeakedRegistrationObjects()
                .penaltyLog()
                .penaltyDeath() // crashes the app on violation (optional for debug)
                .build()

            StrictMode.setThreadPolicy(threadPolicy)
            StrictMode.setVmPolicy(vmPolicy)
        }
    }
}