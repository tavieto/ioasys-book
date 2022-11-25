package ioasys.books.domain.books.di

import ioasys.books.domain.books.usecase.GetBooksUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val booksDomainModule = module {
    factory { (scope: CoroutineScope) ->
        GetBooksUseCase(scope, get())
    }
}
