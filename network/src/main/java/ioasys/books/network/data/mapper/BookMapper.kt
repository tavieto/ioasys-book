package ioasys.books.network.data.mapper

import ioasys.books.domain.books.model.BookDomain
import ioasys.books.network.data.model.BookResponse

fun BookResponse?.mapToDomain(): BookDomain = BookDomain(
    authors = this?.authors ?: listOf(),
    title = this?.title ?: String(),
    category = this?.category ?: String(),
    imageUrl = this?.imageUrl ?: "https://d2drtqy2ezsot0.cloudfront.net/Book-5.jpg",
    id = this?.id ?: String()
)

fun List<BookResponse>?.mapToDomain(): List<BookDomain> = this?.map {
    it.mapToDomain()
} ?: listOf()
