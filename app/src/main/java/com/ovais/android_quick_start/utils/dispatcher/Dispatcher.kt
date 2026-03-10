package com.ovais.android_quick_start.utils.dispatcher

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Background

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Default

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Main