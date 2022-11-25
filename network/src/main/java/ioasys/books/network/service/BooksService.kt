package ioasys.books.network.service

import ioasys.books.network.data.model.BooksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BooksService {
    @GET("books")
    suspend fun getBooks(
        @Header("authorization") authorization: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MWM5YzI5MGNjNDk4YjVjMDg4NDVlMGEiLCJ2bGQiOjE2NjkzMzAzOTY1MDcsImlhdCI6MTY2OTMzMzk5NjUwN30.YiN0aJcmv8400JYnnhCwmOGKQOxZwSzI5ZHt6sQf-hM",
        @Query("page") page: Int = 1,
        @Query("amount") amount: Int = 1
    ): Response<BooksResponse>
}
