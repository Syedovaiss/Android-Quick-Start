package com.ovais.android_quick_start.utils


fun interface SuspendUseCase<out ReturnType> {
    suspend operator fun invoke(): ReturnType
}

fun interface SuspendParameterizedUseCase<in Parameter, out ReturnType> {
    suspend operator fun invoke(param: Parameter): ReturnType
}

fun interface UseCase<out ReturnType> {
    operator fun invoke(): ReturnType
}

fun interface ParameterizedUseCase<in Parameter, out ReturnType> {
    operator fun invoke(param: Parameter): ReturnType
}
