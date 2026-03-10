package com.ovais.android_quick_start.core.initializers

import android.content.Context
import androidx.startup.Initializer
import com.ovais.android_quick_start.core.di.singletonModule
import com.ovais.android_quick_start.features.home.di.homeFactoryModule
import com.ovais.android_quick_start.features.home.di.homeViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        startKoin {
            androidContext(context.applicationContext)
            modules(
                // Singleton
                singletonModule,
                // Home
                homeFactoryModule,
                homeViewModelModule
            )
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> = emptyList()
}