package com.ovais.android_quick_start.core.config

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings

interface DeviceConfigurationManager {
    val deviceIdentifier: String
    val model: String
    val androidVersion: String
}

class DefaultDeviceConfigurationManager(
    private val context: Context
) : DeviceConfigurationManager {
    override val deviceIdentifier: String
        @SuppressLint("HardwareIds")
        get() = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        ) ?: "unknown_device"
    override val model: String
        get() = "${Build.MANUFACTURER} ${Build.MODEL}"
    override val androidVersion: String
        get() = Build.VERSION.RELEASE
}