package me.partypronl.estateagent.presentation.util

import kotlinx.coroutines.flow.MutableStateFlow

sealed interface TypedUIState<out A, out B> {

    fun normalDataOrNull(): A? = (this as? Normal<A>)?.data
    fun errorDataOrNull(): B? = (this as? Error<B>)?.error

    data object Loading : TypedUIState<Nothing, Nothing>
    data class Normal<out A>(val data: A) : TypedUIState<A, Nothing>
    data class Error<out B>(val error: B) : TypedUIState<Nothing, B>
}

fun <A, B> MutableStateFlow<TypedUIState<A, B>>.setLoading() {
    value = TypedUIState.Loading
}

fun <A, B> MutableStateFlow<TypedUIState<A, B>>.setNormal(data: A) {
    value = TypedUIState.Normal(data)
}

fun <A, B> MutableStateFlow<TypedUIState<A, B>>.setError(error: B) {
    value = TypedUIState.Error(error)
}

fun <A> MutableStateFlow<TypedUIState<A, Unit>>.setError() {
    value = TypedUIState.Error(Unit)
}
