package ioasys.books.domain.books.model

data class BookDomain(
    val authors: List<String>,
    val title: String,
    val category: String,
    val imageUrl: String,
    val id: String
)
