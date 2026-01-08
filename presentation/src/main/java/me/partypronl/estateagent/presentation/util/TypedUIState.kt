package me.partypronl.estateagent.presentation.util

import kotlinx.coroutines.flow.MutableStateFlow

sealed interface TypedUiState<out A, out B> {

    fun normalDataOrNull(): A? = (this as? Normal<A>)?.data
    fun errorDataOrNull(): B? = (this as? Error<B>)?.error

    data object Loading : TypedUiState<Nothing, Nothing>
    data class Normal<out A>(val data: A) : TypedUiState<A, Nothing>
    data class Error<out B>(val error: B) : TypedUiState<Nothing, B>
}

fun <A, B> MutableStateFlow<TypedUiState<A, B>>.setLoading() {
    value = TypedUiState.Loading
}

fun <A, B> MutableStateFlow<TypedUiState<A, B>>.setNormal(data: A) {
    value = TypedUiState.Normal(data)
}

fun <A, B> MutableStateFlow<TypedUiState<A, B>>.setError(error: B) {
    value = TypedUiState.Error(error)
}

fun <A> MutableStateFlow<TypedUiState<A, Unit>>.setError() {
    value = TypedUiState.Error(Unit)
}
