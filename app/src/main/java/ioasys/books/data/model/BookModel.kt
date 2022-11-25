package ioasys.books.data.model

data class BookModel(
    val authors: List<String>,
    val title: String,
    val category: String,
    val imageUrl: String,
    val id: String
)
