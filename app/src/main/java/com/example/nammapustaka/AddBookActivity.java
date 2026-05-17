package com.example.nammapustaka;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashSet;
import java.util.Set;

public class AddBookActivity extends AppCompatActivity {

    EditText titleEditText, authorEditText;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Add Book");
        }

        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(v -> {
            String title = titleEditText.getText().toString().trim();
            String author = authorEditText.getText().toString().trim();

            if (title.isEmpty() || author.isEmpty()) {
                Toast.makeText(this, "Please enter title and author", Toast.LENGTH_SHORT).show();
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