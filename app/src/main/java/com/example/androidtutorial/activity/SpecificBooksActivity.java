package com.example.androidtutorial.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidtutorial.R;
import com.example.androidtutorial.adapter.BookRecyclerViewAdapter;
import com.example.androidtutorial.enums.ModeEnum;
import com.example.androidtutorial.model.Book;
import com.example.androidtutorial.model.User;
import com.example.androidtutorial.util.DataUtil;

public class SpecificBooksActivity extends AppCompatActivity {

    private DataUtil dataUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_books);

        dataUtil = DataUtil.getInstance(SpecificBooksActivity.this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        ModeEnum mode = intent != null
                ? ModeEnum.from(intent.getExtras().get("mode").toString())
                : null;

        User currentUser = dataUtil.getCurrentUser();
        List<Book> books;
        if (mode == ModeEnum.CURRENTLY_READING) {
            setTitle(getResources().getText(R.string.activity_currently_reading_books));
            books = dataUtil.getCurrentlyReadingBooks(currentUser.getEmail());
        } else if (mode == ModeEnum.ALREADY_READ) {
            setTitle(getResources().getText(R.string.activity_already_read_books));
            books = dataUtil.getAlreadyReadBooks(currentUser.getEmail());
        } else if (mode == ModeEnum.FAVORITE) {
            setTitle(getResources().getText(R.string.activity_favorite_books));
            books = dataUtil.getFavoriteBooks(currentUser.getEmail());
        } else if (mode == ModeEnum.WISHLIST) {
            setTitle(getResources().getText(R.string.activity_wishlist_books));
            books = dataUtil.getWishlistBooks(currentUser.getEmail());
        } else {
            books = new ArrayList<>();
        }

        BookRecyclerViewAdapter bookRecyclerViewAdapter = new BookRecyclerViewAdapter(this, mode);
        bookRecyclerViewAdapter.setBooks(books);

        RecyclerView recyclerView = findViewById(R.id.specificBooksRecyclerView);
        recyclerView.setAdapter(bookRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}