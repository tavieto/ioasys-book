package ioasys.books.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @ColumnInfo(name = "authors") val authors: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
