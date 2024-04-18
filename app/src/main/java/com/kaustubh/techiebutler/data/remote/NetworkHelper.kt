package com.kaustubh.techiebutler.data.remote

import com.google.gson.JsonParseException
import com.kaustubh.techiebutler.domain.DataError
import com.kaustubh.techiebutler.domain.DataResult
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.IOException

object NetworkConstants {
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"
}

suspend fun <D> doApiCall(apiCall: suspend () -> Call<D>): DataResult<D, DataError> {
    return try {
        val response = apiCall().awaitResponse()
        if (response.isSuccessful) {
            response.body()?.let {
                return DataResult.Success(it)
            }
        }
        handleError(response.code())
    } catch (ex: Exception) {
        handleError(null, ex)
    }
}

private fun <D> handleError(errorCode: Int?, exception: Exception? = null): DataResult<D, DataError> {
    var code = errorCode
    if (code == null && exception is HttpException) code = exception.code()
    return when (exception) {
        is JsonParseException -> DataResult.Error(DataError.PARSE_ERROR)
        is IOException -> DataResult.Error(DataError.NO_INTERNET)
        else -> return when (code) {
            401 -> DataResult.Error(DataError.UNAUTHORIZED)
            else -> DataResult.Error(DataError.OTHERS)
        }
    }
}
