package com.example.nammapustaka;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashSet;
import java.util.Set;

public class ScannerActivity extends AppCompatActivity {

    EditText qrEditText, titleEditText, authorEditText;
    Button fetchBtn, saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Scan QR");
        }

        qrEditText = findViewById(R.id.qrEditText);
        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        fetchBtn = findViewById(R.id.fetchBtn);
        saveBtn = findViewById(R.id.saveBtn);

        fetchBtn.setOnClickListener(v -> {
            String qrData = qrEditText.getText().toString().trim();
            if (qrData.isEmpty()) {
                Toast.makeText(this, "Enter ISBN/QR data", Toast.LENGTH_SHORT).show();
            } else {
                titleEditText.setText("Book from QR: " + qrData);
                authorEditText.setText("Demo Author");
                Toast.makeText(this, "Book details loaded", Toast.LENGTH_SHORT).show();
            }
        });

        saveBtn.setOnClickListener(v -> {
            String title = titleEditText.getText().toString().trim();
            String author = authorEditText.getText().toString().trim();

            if (title.isEmpty() || author.isEmpty()) {
                Toast.makeText(this, "Enter or fetch book details", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences prefs = getSharedPreferences("NammaPustaka", MODE_PRIVATE);
                Set<String> books = prefs.getStringSet("books", new HashSet<>());
                Set<String> newBooks = new HashSet<>(books);
                newBooks.add(title + " by " + author);
                prefs.edit().putStringSet("books", newBooks).apply();

                Toast.makeText(this, "Saved: " + title, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}