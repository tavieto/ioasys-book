package ioasys.books.network.core

import ioasys.books.network.BuildConfig
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit

object Retrofit {

    inline operator fun <reified T> invoke(
        converterFactory: Converter.Factory,
        baseUrl: String,
    ): T = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .client(OkHttp())
        .addConverterFactory(converterFactory)
        .build().create()

    object OkHttp {
        operator fun invoke(): OkHttpClient = OkHttpClient().newBuilder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            connectTimeout(timeout = 30, TimeUnit.SECONDS)
            readTimeout(timeout = 30, TimeUnit.SECONDS)
            writeTimeout(timeout = 30, TimeUnit.SECONDS)
        }.build()
    }
}