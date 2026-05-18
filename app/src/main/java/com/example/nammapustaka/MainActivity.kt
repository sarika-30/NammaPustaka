package com.example.nammapustaka

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.nammapustaka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addBookBtn.setOnClickListener { startActivity(Intent(this, AddBookActivity::class.java)) }
        binding.viewBookBtn.setOnClickListener { startActivity(Intent(this, BookListActivity::class.java)) }
        binding.scanBtn.setOnClickListener { startActivity(Intent(this, ScannerActivity::class.java)) }
        binding.leaderboardBtn.setOnClickListener { startActivity(Intent(this, LeaderboardActivity::class.java)) }
        binding.reviewBtn.setOnClickListener { startActivity(Intent(this, ReviewActivity::class.java)) }
    }
}