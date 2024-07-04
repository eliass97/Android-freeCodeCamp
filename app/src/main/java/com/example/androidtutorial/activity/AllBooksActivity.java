package com.example.androidtutorial.activity;

import java.util.List;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidtutorial.R;
import com.example.androidtutorial.adapter.BookRecyclerViewAdapter;
import com.example.androidtutorial.enums.ModeEnum;
import com.example.androidtutorial.model.Book;
import com.example.androidtutorial.util.DataUtil;

public class AllBooksActivity extends AppCompatActivity {

    private DataUtil dataUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        dataUtil = DataUtil.getInstance(AllBooksActivity.this);
        init();
    }

    private void init() {
        List<Book> books = dataUtil.getBooks();

        BookRecyclerViewAdapter bookRecyclerViewAdapter = new BookRecyclerViewAdapter(this, ModeEnum.ALL);
        bookRecyclerViewAdapter.setBooks(books);

        RecyclerView recyclerView = findViewById(R.id.allBooksRecyclerView);
        recyclerView.setAdapter(bookRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}