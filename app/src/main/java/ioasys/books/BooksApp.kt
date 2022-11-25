package ioasys.books

import android.app.Application
import ioasys.books.di.booksModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BooksApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(booksModule)
        }.androidContext(this@BooksApp)
    }
}
