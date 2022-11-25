package ioasys.books.network.core

import ioasys.books.core.base.Either
import kotlinx.coroutines.delay

class TryCall {
    suspend inline operator fun <reified T> invoke(
        crossinline call: suspend () -> Either<T>,
        max: Int = 3,
        delay: Long = 2000L
    ): Either<T> {
        var mustReturn: Boolean
        var tryCounter = 0
        var result: Either<T> = Either.Failure(Throwable())

        do {
            if (tryCounter == max) break
            tryCounter++

            result = call()

            mustReturn = result.isSuccess

            if (mustReturn.not()) delay(delay)
        } while (mustReturn.not())
        return result
    }
}
