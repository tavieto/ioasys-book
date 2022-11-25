package ioasys.books.common.exception

class UnknownCodeException(
    message: String? = null,
    title: String? = null
) : Throwable(message, Throwable(title))
