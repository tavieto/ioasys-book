package ioasys.books.network.core

import ioasys.books.common.exception.*
import retrofit2.Response

class NetworkWrapper {

    companion object StatusCode {
        const val BAD_REQUEST = 400
        const val NOT_AUTHORIZED = 401
        const val NOT_FOUND = 404
        const val TIMEOUT = 408
    }

    suspend inline operator fun <reified T> invoke(
        crossinline call: suspend () -> Response<T>
    ): T = call().let { result ->
        return when {
            result.isSuccessful.not() -> throw handleExceptionByCode(result)
            else -> result.body() ?: throw MissingParamsException()
        }
    }

    fun handleExceptionByCode(response: Response<*>): Throwable {
        return when(response.code()) {
            BAD_REQUEST -> InvalidDataException(response.message())
            NOT_FOUND -> NotFoundException(response.message())
            NOT_AUTHORIZED -> UnauthorizedException(response.message())
            TIMEOUT -> TimeOutException(response.message())
            else -> UnknownCodeException(response.message())
        }
    }
}