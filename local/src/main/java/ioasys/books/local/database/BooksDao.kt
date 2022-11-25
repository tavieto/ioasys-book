package ioasys.books.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BooksDao {
    @Query("SELECT * FROM books")
    suspend fun getAll(): List<BookEntity>

    @Insert
    suspend fun insertAll(vararg users: BookEntity)
}
