package com.example.bookshelf.repository

import android.util.Log
import com.example.bookshelf.model.Book
import com.example.bookshelf.network.NetworkModule
import kotlinx.coroutines.delay
import retrofit2.HttpException

class BooksRepository {
    private val service = NetworkModule.booksService

    suspend fun searchBooks(query: String): List<Book> {
        var attempt = 0
        var result: List<Book>? = null

        while (attempt < 3) {
            try {
                val response = service.searchBooks(query)
                Log.d("BooksRepository", "API Response: ${response.items}") // Log the response for debugging
                result = response.items?.map { bookResponse ->
                    Book(
                        title = bookResponse.volumeInfo.title,
                        thumbnailUrl = bookResponse.volumeInfo.imageLinks?.thumbnail?.replace("http", "https")
                    )
                } ?: emptyList()
                break
            } catch (e: HttpException) {
                if (e.code() == 429) {
                    attempt++
                    delay((1000 * attempt).toLong())
                } else {
                    throw e
                }
            }
        }
        return result ?: emptyList()
    }
}
