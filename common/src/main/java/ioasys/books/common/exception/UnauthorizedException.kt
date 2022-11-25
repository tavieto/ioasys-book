package ioasys.books.common.exception

class UnauthorizedException(
    message: String? = null,
    title: String? = null
) : Throwable(message, Throwable(title))
