package ioasys.books

import ioasys.books.data.model.BookModel

data class BooksViewState(
    val books: List<BookModel> = listOf(),
    val isLoading: Boolean = false
)
