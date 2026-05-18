package com.example.nammapustaka

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nammapustaka.databinding.ActivityLeaderboardBinding

class LeaderboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLeaderboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prefs = getSharedPreferences("books", Context.MODE_PRIVATE)
        val books = prefs.getStringSet("BOOKS", setOf())?: setOf()
        val bookCount = books.size

        val reviewPrefs = getSharedPreferences("reviews", Context.MODE_PRIVATE)
        val reviewCount = reviewPrefs.getStringSet("REVIEWS", setOf())?.size?: 0

        val rank = when {
            bookCount >= 50 -> "🏆 Book Master"
            bookCount >= 20 -> "📚 Book Worm"
            bookCount >= 10 -> "📖 Avid Reader"
            bookCount >= 5 -> "👍 Reader"
            else -> "🌱 Newbie"
        }

        binding.tvLeaderboard.text = """
            $rank
            
            Books Added: $bookCount
            Reviews Written: $reviewCount
            
            Keep reading to level up!
            
            Next Rank:
            ${if (bookCount < 5) "5 books → Reader"
        else if (bookCount < 10) "10 books → Avid Reader"
        else if (bookCount < 20) "20 books → Book Worm"
        else if (bookCount < 50) "50 books → Book Master"
        else "You've reached max rank!"}
        """.trimIndent()
    }
}