package ioasys.books.local.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ioasys.books.domain.books.model.BookDomain
import ioasys.books.local.database.BookEntity

fun BookEntity.mapToDomain(): BookDomain {
    val gson = Gson()
    val itemType = object : TypeToken<List<String>>() {}.type

    return BookDomain(
        authors = gson.fromJson(this.authors, itemType),
        title = this.title,
        category = this.category,
        imageUrl = this.imageUrl,
        id = this.id.toString()
    )
}

fun List<BookEntity>.mapToDomain(): List<BookDomain> = this.map {
    it.mapToDomain()
}

fun BookDomain.mapFromDomain(): BookEntity = BookEntity(
    authors = Gson().toJson(this.authors),
    title = this.title,
    category = this.category,
    imageUrl = this.imageUrl
)

fun List<BookDomain>.mapFromDomain(): List<BookEntity> = this.map {
    it.mapFromDomain()
}
