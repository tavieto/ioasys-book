package ioasys.books.common.exception

class TimeOutException(
    message: String? = null,
    title: String? = null
) : Throwable(message, Throwable(title))
