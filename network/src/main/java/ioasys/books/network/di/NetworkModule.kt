package ioasys.books.network.di

import ioasys.books.network.core.NetworkMonitor
import ioasys.books.network.core.NetworkWrapper
import ioasys.books.network.core.Retrofit
import ioasys.books.network.core.TryCall
import ioasys.books.network.datasource.BooksRemoteDataSourceImpl
import ioasys.books.network.service.BooksService
import ioasys.books.repository.datasource.network.BooksRemoteDataSource
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { NetworkWrapper() }
    single { NetworkMonitor(get()) }
    single { TryCall() }
    single<BooksRemoteDataSource> { BooksRemoteDataSourceImpl(get(), get(), get(), get()) }

    factory<BooksService> {
        Retrofit(
            baseUrl = "https://books.ioasys.com.br/api/v1/",
            converterFactory = GsonConverterFactory.create()
        )
    }
}