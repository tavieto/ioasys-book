package ioasys.books

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ioasys.books.BooksViewAction.Get
import ioasys.books.BooksViewAction.Set
import ioasys.books.data.mapper.mapFromDomain
import ioasys.books.domain.books.usecase.GetBooksUseCase
import ioasys.books.util.useCase
import org.koin.core.component.KoinComponent

class BooksViewModel : ViewModel(), KoinComponent {

    private val getBooks: GetBooksUseCase by useCase()
    var viewState by mutableStateOf(BooksViewState())
        private set

    fun dispatchAction(action: BooksViewAction) {
        when (action) {
            is Get.Books -> getBooks()
            is Set.Loading -> setLoading(isLoading = action.isLoading)
        }
    }

    private fun getBooks() {
        dispatchAction(Set.Loading(true))
        getBooks(
            onSuccess = {
                viewState = viewState.copy(
                    books = it.mapFromDomain().reversed()
                )
                dispatchAction(Set.Loading(false))
            },
            onFailure = {
                dispatchAction(Set.Loading(false))
            }
        )
    }

    private fun setLoading(isLoading: Boolean) {
        viewState = viewState.copy(isLoading = isLoading)
    }
}
