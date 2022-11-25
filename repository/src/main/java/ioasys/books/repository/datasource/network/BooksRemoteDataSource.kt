package ioasys.books.repository.datasource.network

import ioasys.books.core.base.Either
import ioasys.books.domain.books.model.BookDomain
import kotlinx.coroutines.flow.Flow

interface BooksRemoteDataSource {
    suspend fun getBooks(): Flow<Either<List<BookDomain>>>
}
