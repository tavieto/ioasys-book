package ioasys.books.network.data.model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("authors")
    val authors: List<String>? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("imageUrl")
    val imageUrl: String? = null,
    @SerializedName("id")
    val id: String? = null
)
