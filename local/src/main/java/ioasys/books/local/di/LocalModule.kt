package ioasys.books.local.di

import androidx.room.Room
import ioasys.books.local.database.BooksDatabase
import ioasys.books.local.datasource.BooksLocalDataSourceImpl
import ioasys.books.repository.datasource.local.BooksLocalDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            BooksDatabase::class.java,
            "books_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        get<BooksDatabase>().booksDao()
    }

    single<BooksLocalDataSource> { BooksLocalDataSourceImpl(get()) }
}
