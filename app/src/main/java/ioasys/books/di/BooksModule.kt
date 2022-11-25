package ioasys.books.di

import ioasys.books.BooksViewModel
import ioasys.books.domain.books.di.booksDomainModule
import ioasys.books.local.di.localModule
import ioasys.books.network.di.networkModule
import ioasys.books.repository.di.repositoryModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val booksModule = module {
    viewModel { BooksViewModel() }

    loadKoinModules(
        listOf(
            networkModule,
            repositoryModule,
            booksDomainModule,
            localModule
        )
    )
}