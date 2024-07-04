package com.example.androidtutorial.adapter;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;
import com.bumptech.glide.Glide;
import com.example.androidtutorial.R;
import com.example.androidtutorial.activity.BookViewActivity;
import com.example.androidtutorial.enums.ModeEnum;
import com.example.androidtutorial.model.Book;
import com.example.androidtutorial.model.BookUser;
import com.example.androidtutorial.model.User;
import com.example.androidtutorial.util.DataUtil;

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {

    private final DataUtil dataUtil;
    private final Context context;
    private final ModeEnum mode;
    private List<Book> books = new ArrayList<>();

    public BookRecyclerViewAdapter(Context context, ModeEnum mode) {
        this.dataUtil = DataUtil.getInstance(context);
        this.context = context;
        this.mode = mode;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.books_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Book book = books.get(position);

        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.pages.setText(String.valueOf(book.getPages()));
        holder.shortDescription.setText(book.getShortDescription());
        holder.fullDescription.setText(book.getFullDescription());

        Glide.with(context).asBitmap().load(books.get(position).getImageUrl()).into(holder.image);

        holder.parent.setOnClickListener(view -> {
            Intent intent = new Intent(context, BookViewActivity.class);
            intent.putExtra("bookId", book.getId());
            context.startActivity(intent);
        });

        holder.expandBtn.setOnClickListener(view -> {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.collapsableLayout.setVisibility(View.VISIBLE);
            holder.expandBtn.setVisibility(View.GONE);
        });

        holder.collapseBtn.setOnClickListener(view -> {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.collapsableLayout.setVisibility(View.GONE);
            holder.expandBtn.setVisibility(View.VISIBLE);
        });

        if (mode == ModeEnum.ALL) {
            holder.removeBtn.setVisibility(View.GONE);
        } else {
            holder.removeBtn.setVisibility(View.VISIBLE);
            holder.removeBtn.setOnClickListener(view -> {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure you want to remove this book from this list?");
                alertDialogBuilder.setPositiveButton("Yes", (dialogInterface, i) -> removeBook(position));
                alertDialogBuilder.setNegativeButton("No", (dialogInterface, i) -> {
                });
                alertDialogBuilder.create().show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    private void removeBook(int position) {
        User currentUser = dataUtil.getCurrentUser();
        Book book = books.get(position);
        BookUser bookUser = dataUtil.getBookUserCorrelation(currentUser.getEmail(), book.getId());
        if (mode == ModeEnum.FAVORITE) {
            bookUser.setFavorite(false);
        } else if (mode == ModeEnum.WISHLIST) {
            bookUser.setWishlist(false);
        } else if (mode == ModeEnum.CURRENTLY_READING) {
            bookUser.setCurrentlyReading(false);
        } else if (mode == ModeEnum.ALREADY_READ) {
            bookUser.setAlreadyRead(false);
        }
        dataUtil.updateBookUserCorrelation(bookUser);
        books.remove(position);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title, author, pages, shortDescription, fullDescription;
        private final ImageView image;
        private final CardView parent;
        private final RelativeLayout collapsableLayout;
        private final ImageView expandBtn, collapseBtn, removeBtn;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.bookTitle);
            author = itemView.findViewById(R.id.bookAuthorTextView);
            pages = itemView.findViewById(R.id.bookPagesTextView);
            shortDescription = itemView.findViewById(R.id.bookShortDescriptionTextView);
            fullDescription = itemView.findViewById(R.id.bookFullDescriptionTextView);
            image = itemView.findViewById(R.id.bookImage);

            parent = itemView.findViewById(R.id.booksListRootLayout);
            collapsableLayout = itemView.findViewById(R.id.bookCollapsableLayout);

            expandBtn = itemView.findViewById(R.id.bookExpandBtn);
            collapseBtn = itemView.findViewById(R.id.bookCollapseBtn);
            removeBtn = itemView.findViewById(R.id.bookRemoveBtn);
        }
    }
}
