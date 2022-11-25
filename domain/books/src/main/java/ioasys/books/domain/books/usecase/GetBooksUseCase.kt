package ioasys.books.domain.books.usecase

import ioasys.books.core.base.Either
import ioasys.books.core.base.UseCase
import ioasys.books.domain.books.model.BookDomain
import ioasys.books.domain.books.repository.BooksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetBooksUseCase(
    scope: CoroutineScope,
    private val repository: BooksRepository
): UseCase<Unit, List<BookDomain>>(scope) {

    override suspend fun run(
        params: Unit?
    ): Flow<Either<List<BookDomain>>> = repository.getBooks()
}
