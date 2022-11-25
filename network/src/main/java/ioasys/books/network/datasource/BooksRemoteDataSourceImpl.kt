package ioasys.books.network.datasource

import android.accounts.NetworkErrorException
import ioasys.books.core.base.Either
import ioasys.books.core.base.mapCatching
import ioasys.books.core.base.runCatchData
import ioasys.books.domain.books.model.BookDomain
import ioasys.books.network.core.NetworkMonitor
import ioasys.books.network.core.NetworkWrapper
import ioasys.books.network.core.TryCall
import ioasys.books.network.data.mapper.mapToDomain
import ioasys.books.network.service.BooksService
import ioasys.books.repository.datasource.network.BooksRemoteDataSource
import kotlinx.coroutines.flow.*

internal class BooksRemoteDataSourceImpl(
    private val tryCall: TryCall,
    private val networkState: NetworkMonitor,
    private val service: BooksService,
    private val networkWrapper: NetworkWrapper
) : BooksRemoteDataSource {

    override suspend fun getBooks(): Flow<Either<List<BookDomain>>> = callbackFlow {
        networkState.isOnline().collect { isOnline ->
            if (isOnline) {
                send(
                    tryCall.invoke(
                        call = {
                            runCatchData {
                                networkWrapper {
                                    service.getBooks()
                                }
                            }.mapCatching {
                                it.data.mapToDomain()
                            }
                        }
                    )
                )
            } else {
                send(Either.Failure(error = NetworkErrorException()))
            }

        }
    }
}
