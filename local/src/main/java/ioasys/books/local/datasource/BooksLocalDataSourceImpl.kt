package ioasys.books.local.datasource

import ioasys.books.core.base.Either
import ioasys.books.core.base.runCatchData
import ioasys.books.domain.books.model.BookDomain
import ioasys.books.local.database.BooksDao
import ioasys.books.local.database.BooksDatabase
import ioasys.books.local.mapper.mapFromDomain
import ioasys.books.local.mapper.mapToDomain
import ioasys.books.repository.datasource.local.BooksLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class BooksLocalDataSourceImpl(
    private val db: BooksDao
) : BooksLocalDataSource {
    override suspend fun getBooks(): Flow<Either<List<BookDomain>>> = flow {
        emit(
            runCatchData {
                db.getAll().mapToDomain()
            }
        )
    }

    override suspend fun saveBooks(
        books: List<BookDomain>
    ): Flow<Either<Unit>> = flow {
        emit(
            runCatchData {
                db.insertAll(*books.mapFromDomain().toTypedArray())
            }
        )
    }

    private companion object {
        const val BOOKS_KEY = "books"
    }
}
