package ioasys.books.domain.books.repository

import ioasys.books.core.base.Either
import ioasys.books.domain.books.model.BookDomain
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    suspend fun getBooks(): Flow<Either<List<BookDomain>>>
}
