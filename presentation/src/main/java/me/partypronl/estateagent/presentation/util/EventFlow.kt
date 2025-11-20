package me.partypronl.estateagent.presentation.util

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

typealias EventFlow<T> = SharedFlow<T>

fun <T> MutableEventFlow(
    replay: Int = 0,
    extraBufferCapacity: Int = 1,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND
): MutableSharedFlow<T> {
    return MutableSharedFlow(replay, extraBufferCapacity, onBufferOverflow)
}

fun <T> MutableSharedFlow<T>.asEventFlow() = this.asSharedFlow()

fun <T> MutableSharedFlow<T>.emitEvent(event: T) = this.tryEmit(event)
