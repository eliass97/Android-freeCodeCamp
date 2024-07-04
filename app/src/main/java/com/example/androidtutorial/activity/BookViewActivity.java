package com.example.androidtutorial.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.androidtutorial.R;
import com.example.androidtutorial.model.Book;
import com.example.androidtutorial.model.BookUser;
import com.example.androidtutorial.util.DataUtil;

public class BookViewActivity extends AppCompatActivity {

    private DataUtil dataUtil;

    private ImageView image;
    private TextView title, author, pages, shortDescription, fullDescription;
    private Button currentlyReadingBtn, alreadyReadBtn, favoriteBtn, wishlistBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);

        dataUtil = DataUtil.getInstance(BookViewActivity.this);
        init();
    }

    private void init() {
        setVariables();

        Long bookId = getIntent().getExtras().getLong("bookId");
        String userEmail = dataUtil.getCurrentUser().getEmail();

        BookUser bookUser = dataUtil.getBookUserCorrelation(userEmail, bookId);
        Book book = bookUser.getBook();

        setTitle(book.getTitle());

        String titleString = "Title: " + book.getTitle();
        title.setText(titleString);
        String authorString = "Author: " + book.getAuthor();
        author.setText(authorString);
        String pagesString = "Pages: " + book.getPages();
        pages.setText(pagesString);
        String shortDescriptionString = "Short description:\n" + book.getShortDescription();
        shortDescription.setText(shortDescriptionString);
        String fullDescriptionString = "Full description:\n" + book.getFullDescription();
        fullDescription.setText(fullDescriptionString);

        Glide.with(this).asBitmap().load(book.getImageUrl()).into(image);

        currentlyReadingBtn.setEnabled(Boolean.FALSE.equals(bookUser.isCurrentlyReading()));
        currentlyReadingBtn.setOnClickListener(view -> {
            bookUser.setCurrentlyReading(true);
            dataUtil.updateBookUserCorrelation(bookUser);
            currentlyReadingBtn.setEnabled(false);
        });

        alreadyReadBtn.setEnabled(Boolean.FALSE.equals(bookUser.isAlreadyRead()));
        alreadyReadBtn.setOnClickListener(view -> {
            bookUser.setAlreadyRead(true);
            dataUtil.updateBookUserCorrelation(bookUser);
            alreadyReadBtn.setEnabled(false);
        });

        favoriteBtn.setEnabled(Boolean.FALSE.equals(bookUser.isFavorite()));
        favoriteBtn.setOnClickListener(view -> {
            bookUser.setFavorite(true);
            dataUtil.updateBookUserCorrelation(bookUser);
            favoriteBtn.setEnabled(false);
        });

        wishlistBtn.setEnabled(Boolean.FALSE.equals(bookUser.isWishlist()));
        wishlistBtn.setOnClickListener(view -> {
            bookUser.setWishlist(true);
            dataUtil.updateBookUserCorrelation(bookUser);
            wishlistBtn.setEnabled(false);
        });
    }

    private void setVariables() {
        image = findViewById(R.id.bookViewImage);
        title = findViewById(R.id.bookViewTitle);
        author = findViewById(R.id.bookViewAuthor);
        pages = findViewById(R.id.bookViewPages);
        shortDescription = findViewById(R.id.bookViewShortDescription);
        fullDescription = findViewById(R.id.bookViewFullDescription);
        currentlyReadingBtn = findViewById(R.id.bookViewCurrentlyReadingBtn);
        alreadyReadBtn = findViewById(R.id.bookViewAlreadyReadBtn);
        favoriteBtn = findViewById(R.id.bookViewFavoritesBtn);
        wishlistBtn = findViewById(R.id.bookViewWishlistBtn);
    }
}