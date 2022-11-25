package ioasys.books

sealed class BooksViewAction {
    object Get {
        object Books: BooksViewAction()
    }

    object Set {
        data class Loading(val isLoading: Boolean) : BooksViewAction()
    }
}
