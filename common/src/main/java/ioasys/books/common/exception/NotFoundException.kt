package ioasys.books.common.exception

class NotFoundException(
    message: String? = null,
    title: String? = null
) : Throwable(message, Throwable(title))
