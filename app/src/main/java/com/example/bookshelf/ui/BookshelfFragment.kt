package com.example.bookshelf.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshelf.R
import com.example.bookshelf.adapter.BooksAdapter
import com.example.bookshelf.viewmodel.BooksViewModel

class BookshelfFragment : Fragment(R.layout.fragment_bookshelf) {
    private val viewModel: BooksViewModel by viewModels()
    private lateinit var adapter: BooksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.books_recycler_view)
        val loadingIndicator = view.findViewById<ProgressBar>(R.id.loading_indicator)

        recyclerView.layoutManager = GridLayoutManager(context, 2)
        adapter = BooksAdapter(emptyList())
        recyclerView.adapter = adapter

        viewModel.booksLiveData.observe(viewLifecycleOwner) { books ->
            adapter = BooksAdapter(books) // Update adapter’s list
            recyclerView.adapter = adapter
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            loadingIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Example search
        viewModel.searchBooks("jazz history")
    }
}

