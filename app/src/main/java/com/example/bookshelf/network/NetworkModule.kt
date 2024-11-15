package com.example.bookshelf.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object NetworkModule {
    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    val booksService: BooksService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksService::class.java)
    }
}

interface BooksService {
    @GET("volumes")
    suspend fun searchBooks(@Query("q") query: String): BooksResponse
}

data class BooksResponse(val items: List<BookResponse>?)

data class BookResponse(val volumeInfo: VolumeInfo)

data class VolumeInfo(
    val title: String,
    val imageLinks: ImageLinks?
)

data class ImageLinks(val thumbnail: String?)
