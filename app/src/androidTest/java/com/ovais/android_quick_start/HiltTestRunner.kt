package com.ovais.android_quick_start

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

class HiltTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        // Use HiltTestApplication instead of your regular Application
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}