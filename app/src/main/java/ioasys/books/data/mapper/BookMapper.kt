package ioasys.books.data.mapper

import ioasys.books.data.model.BookModel
import ioasys.books.domain.books.model.BookDomain

fun BookDomain.mapFromDomain(): BookModel = BookModel(
    authors = this.authors,
    title = this.title,
    category = this.category,
    imageUrl = this.imageUrl,
    id = this.id
)

fun List<BookDomain>.mapFromDomain(): List<BookModel> = this.map {
    it.mapFromDomain()
}
