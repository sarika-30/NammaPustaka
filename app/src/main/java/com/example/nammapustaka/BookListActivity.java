package com.example.nammapustaka;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BookListActivity extends AppCompatActivity {

    ListView bookListView;
    TextView emptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Saved Books");
        }

        bookListView = findViewById(R.id.bookListView);
        emptyText = findViewById(R.id.emptyText);

        SharedPreferences prefs = getSharedPreferences("NammaPustaka", MODE_PRIVATE);
        Set<String> books = prefs.getStringSet("books", new HashSet<>());

        if (books.isEmpty()) {
            emptyText.setText("No books saved yet");
        } else {
            ArrayList<String> bookList = new ArrayList<>(books);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, bookList);
            bookListView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}