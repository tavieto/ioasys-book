package ioasys.books.repository.datasource.local

import ioasys.books.core.base.Either
import ioasys.books.domain.books.model.BookDomain
import kotlinx.coroutines.flow.Flow

interface BooksLocalDataSource {
    suspend fun getBooks(): Flow<Either<List<BookDomain>>>
    suspend fun saveBooks(books: List<BookDomain>): Flow<Either<Unit>>
}
