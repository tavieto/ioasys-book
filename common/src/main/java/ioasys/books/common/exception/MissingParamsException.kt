package ioasys.books.common.exception

class MissingParamsException(
    message: String? = null,
    title: String? = null
) : Throwable(message, Throwable(title))
