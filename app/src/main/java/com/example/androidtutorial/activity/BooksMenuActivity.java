package com.example.androidtutorial.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidtutorial.R;
import com.example.androidtutorial.enums.ModeEnum;
import com.example.androidtutorial.util.DataUtil;

public class BooksMenuActivity extends AppCompatActivity {

    private DataUtil dataUtil;

    private Button allBooksBtn, currentlyReadingBooksBtn, alreadyReadBooksBtn, favoriteBooksBtn, wishlistBooksBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_menu);

        dataUtil = DataUtil.getInstance(BooksMenuActivity.this);
        init();
    }

    private void init() {
        setVariables();

        allBooksBtn.setOnClickListener(view -> {
            Intent intent = new Intent(BooksMenuActivity.this, AllBooksActivity.class);
            startActivity(intent);
        });

        currentlyReadingBooksBtn.setOnClickListener(view -> {
            Intent intent = new Intent(BooksMenuActivity.this, SpecificBooksActivity.class);
            intent.putExtra("mode", ModeEnum.CURRENTLY_READING.getValue());
            startActivity(intent);
        });

        alreadyReadBooksBtn.setOnClickListener(view -> {
            Intent intent = new Intent(BooksMenuActivity.this, SpecificBooksActivity.class);
            intent.putExtra("mode", ModeEnum.ALREADY_READ.getValue());
            startActivity(intent);
        });

        favoriteBooksBtn.setOnClickListener(view -> {
            Intent intent = new Intent(BooksMenuActivity.this, SpecificBooksActivity.class);
            intent.putExtra("mode", ModeEnum.FAVORITE.getValue());
            startActivity(intent);
        });

        wishlistBooksBtn.setOnClickListener(view -> {
            Intent intent = new Intent(BooksMenuActivity.this, SpecificBooksActivity.class);
            intent.putExtra("mode", ModeEnum.WISHLIST.getValue());
            startActivity(intent);
        });
    }

    private void setVariables() {
        allBooksBtn = findViewById(R.id.books_AllBooksBtn);
        currentlyReadingBooksBtn = findViewById(R.id.books_CurrentlyReadingBooksBtn);
        alreadyReadBooksBtn = findViewById(R.id.books_AlreadyReadBooksBtn);
        favoriteBooksBtn = findViewById(R.id.books_FavoriteBooksBtn);
        wishlistBooksBtn = findViewById(R.id.books_WishlistBtn);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings_menu) {
            Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.logout) {
            dataUtil.logout();
            Intent intent = new Intent(BooksMenuActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}