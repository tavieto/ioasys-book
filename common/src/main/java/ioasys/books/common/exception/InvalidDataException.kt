package ioasys.books.common.exception

class InvalidDataException(
    message: String? = null,
    title: String? = null
) : Throwable(message, Throwable(title))
