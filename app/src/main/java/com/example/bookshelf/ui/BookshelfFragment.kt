package com.example.bookshelf.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshelf.R
import com.example.bookshelf.adapter.BooksAdapter
import com.example.bookshelf.viewmodel.BooksViewModel
import androidx.appcompat.widget.Toolbar

class BookshelfFragment : Fragment(R.layout.fragment_bookshelf) {
    private val viewModel: BooksViewModel by viewModels()
    private lateinit var adapter: BooksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        toolbar.title = "Pokémon Books"


        val recyclerView = view.findViewById<RecyclerView>(R.id.books_recycler_view)
        val loadingIndicator = view.findViewById<View>(R.id.loading_indicator)

        recyclerView.layoutManager = GridLayoutManager(context, 2)
        adapter = BooksAdapter(emptyList())
        recyclerView.adapter = adapter


        viewModel.booksLiveData.observe(viewLifecycleOwner) { books ->
            adapter = BooksAdapter(books)
            recyclerView.adapter = adapter
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            loadingIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
        }


        viewModel.searchBooks("Pokémon")
    }
}
