package ioasys.books.repository.repository

import ioasys.books.core.base.Either
import ioasys.books.core.base.Either.Failure
import ioasys.books.core.base.Either.Success
import ioasys.books.domain.books.model.BookDomain
import ioasys.books.domain.books.repository.BooksRepository
import ioasys.books.repository.datasource.local.BooksLocalDataSource
import ioasys.books.repository.datasource.network.BooksRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class BooksRepositoryImpl(
    private val remote: BooksRemoteDataSource,
    private val local: BooksLocalDataSource
): BooksRepository {
    override suspend fun getBooks(): Flow<Either<List<BookDomain>>> = flow {
        remote.getBooks().collect { response ->
            when (response) {
                is Success -> {
                    local.saveBooks(response.data).collect {
                        emitAll(local.getBooks())
                    }
                }
                is Failure -> emitAll(local.getBooks())
            }
        }

        emitAll(local.getBooks())
    }
}
