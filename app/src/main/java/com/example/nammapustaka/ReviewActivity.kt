package com.example.nammapustaka

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nammapustaka.databinding.ActivityReviewBinding

class ReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadReviews()

        binding.btnSaveReview.setOnClickListener {
            val title = binding.etBookTitle.text.toString().trim()
            val review = binding.etReview.text.toString().trim()

            if (title.isEmpty() || review.isEmpty()) {
                Toast.makeText(this, "Enter book title and review", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            saveReview(title, review)
            binding.etBookTitle.text.clear()
            binding.etReview.text.clear()
            loadReviews()
            Toast.makeText(this, "Review saved!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveReview(title: String, review: String) {
        val prefs = getSharedPreferences("reviews", Context.MODE_PRIVATE)
        val reviews = prefs.getStringSet("REVIEWS", mutableSetOf())?.toMutableSet()?: mutableSetOf()
        reviews.add("$title|--|$review")
        prefs.edit().putStringSet("REVIEWS", reviews).apply()
    }

    private fun loadReviews() {
        val prefs = getSharedPreferences("reviews", Context.MODE_PRIVATE)
        val reviews = prefs.getStringSet("REVIEWS", setOf())?: setOf()

        if (reviews.isEmpty()) {
            binding.tvSavedReviews.text = "No reviews yet. Add your first one!"
        } else {
            val displayText = StringBuilder()
            reviews.forEach { entry ->
                val parts = entry.split("|--|")
                if (parts.size == 2) {
                    displayText.append("📖 ${parts[0]}\n${parts[1]}\n\n")
                }
            }
            binding.tvSavedReviews.text = displayText.toString()
        }
    }
}