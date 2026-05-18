package com.example.nammapustaka

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nammapustaka.databinding.ActivityAddBookBinding

class AddBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.saveBtn.setOnClickListener {
            val title = binding.titleEditText.text.toString().trim()
            if (title.isEmpty()) {
                Toast.makeText(this, "Enter book title", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val prefs = getSharedPreferences("books", Context.MODE_PRIVATE)
            val books = prefs.getStringSet("BOOKS", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
            books.add(title)
            prefs.edit().putStringSet("BOOKS", books).apply()
            Toast.makeText(this, "Saved: $title", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}