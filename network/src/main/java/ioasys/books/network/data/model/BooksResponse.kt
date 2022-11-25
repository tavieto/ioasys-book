package ioasys.books.network.data.model

import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("data")
    val data: List<BookResponse>? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("totalItems")
    val totalItems: Int? = null,
    @SerializedName("totalPages")
    val totalPages: Int? = null
)
