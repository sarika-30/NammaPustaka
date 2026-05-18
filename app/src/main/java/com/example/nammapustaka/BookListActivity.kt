package com.example.nammapustaka

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nammapustaka.databinding.ActivityBookListBinding

class BookListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prefs = getSharedPreferences("books", Context.MODE_PRIVATE)
        val books = prefs.getStringSet("BOOKS", setOf())?.sorted() ?: listOf("No books added")
        binding.bookListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, books)
    }
}