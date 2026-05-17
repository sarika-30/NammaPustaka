package com.example.nammapustaka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button addBookBtn, viewBookBtn, scanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBookBtn = findViewById(R.id.addBookBtn);
        viewBookBtn = findViewById(R.id.viewBookBtn);
        scanBtn = findViewById(R.id.scanBtn);

        addBookBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            startActivity(intent);
        });

        viewBookBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BookListActivity.class);
            startActivity(intent);
        });

        scanBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ScannerActivity.class);
            startActivity(intent);
        });
    }
}