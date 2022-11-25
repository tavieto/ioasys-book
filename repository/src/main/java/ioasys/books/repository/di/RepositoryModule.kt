package ioasys.books.repository.di

import ioasys.books.domain.books.repository.BooksRepository
import ioasys.books.repository.repository.BooksRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<BooksRepository> { BooksRepositoryImpl(get(), get()) }
}
