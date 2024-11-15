package com.example.bookshelf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.bookshelf.model.Book
import com.example.bookshelf.repository.BooksRepository

class BooksViewModel : ViewModel() {
    private val repository = BooksRepository()
    val booksLiveData = MutableLiveData<List<Book>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun searchBooks(query: String) {
        viewModelScope.launch {
            loadingLiveData.value = true
            try {
                val books = repository.searchBooks(query)
                booksLiveData.value = books
            } finally {
                loadingLiveData.value = false
            }
        }
    }
}
