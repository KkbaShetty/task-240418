package com.kaustubh.techiebutler.domain

sealed interface DataResult<out D, out E : ResultError> {
    class Loading<out D, out E : ResultError> : DataResult<D, E>
    data class Success<out D, out E : ResultError>(val data: D) : DataResult<D, E>
    data class Error<out D, out E : ResultError>(val error: E) : DataResult<D, E>
}

sealed interface ResultError

enum class DataError : ResultError {
    PARSE_ERROR,
    NO_INTERNET,
    UNAUTHORIZED,
    OTHERS,
}
