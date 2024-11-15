package com.example.bookshelf


data class BooksResponse(
    val items: List<BookResponse>?
)

data class BookResponse(
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val imageLinks: ImageLinks?
)

data class ImageLinks(
    val thumbnail: String?
)
