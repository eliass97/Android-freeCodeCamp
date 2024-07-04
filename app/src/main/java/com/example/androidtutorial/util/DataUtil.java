package com.example.androidtutorial.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.widget.ProgressBar;
import com.example.androidtutorial.model.Book;
import com.example.androidtutorial.model.BookUser;
import com.example.androidtutorial.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DataUtil {

    private static DataUtil instance;
    private final Gson gson;
    private final SharedPreferences sharedPreferences;

    private static final String USER_SOURCE = "DB_USER_SOURCE";
    private static final String BOOK_SOURCE = "DB_BOOK_SOURCE";
    private static final String USER_BOOK_SOURCE = "DB_USER_BOOK_SOURCE";
    private static final String CURRENT_USER_SOURCE = "DB_CURRENT_USER_SOURCE";

    public static DataUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (DataUtil.class) {
                instance = new DataUtil(context);
            }
        }
        return instance;
    }

    private DataUtil(Context context) {
        gson = new Gson();
        sharedPreferences = context.getSharedPreferences("local_db", Context.MODE_PRIVATE);
        if (getBooks() == null) initData();
    }

    private void initData() {
        List<User> users = new ArrayList<>();

        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "1984", "George Orwell", 350,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam imperdiet nunc ultricies justo commodo, a finibus mauris lacinia. Phasellus et bibendum erat, vel facilisis arcu.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam imperdiet nunc ultricies justo commodo, a finibus mauris lacinia. Phasellus et bibendum erat, vel facilisis arcu. Pellentesque fermentum dui ligula, vel pharetra ex consequat sed. Sed tempus rutrum ultrices. Cras commodo velit vitae arcu iaculis, quis aliquet ipsum mattis. Phasellus sit amet enim urna. Duis nec malesuada turpis. Pellentesque ac purus dolor. Cras et scelerisque massa. Donec sed leo bibendum, finibus nibh vitae, feugiat massa. Nulla tempor massa libero, ut fringilla massa tristique ullamcorper. Vestibulum pellentesque massa semper, suscipit orci pulvinar, aliquet velit. Aenean dictum faucibus pulvinar. Etiam sit amet finibus elit. Vivamus tempus justo sed porttitor fermentum.\n" +
                "\n" +
                "Nullam efficitur lobortis rhoncus. Ut vel quam facilisis, consectetur tellus at, dignissim quam. Ut ullamcorper mi eget dui laoreet vestibulum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec quis turpis augue. Quisque hendrerit felis id turpis aliquet porta. Morbi eget ullamcorper ipsum.\n" +
                "\n" +
                "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec laoreet nisl sed ex accumsan eleifend. Maecenas vel ipsum turpis. Phasellus maximus eros orci, quis laoreet eros interdum faucibus. Aenean arcu turpis, malesuada id ex a, elementum semper lorem. Fusce libero sapien, scelerisque sed felis sit amet, suscipit volutpat nisl. Donec laoreet, nisl sit amet dignissim mollis, orci felis cursus risus, at semper lacus est at lacus. Curabitur dapibus tempor elementum.",
                "https://upload.wikimedia.org/wikipedia/commons/6/6b/1984-Big-Brother.jpg"));
        books.add(new Book(2L, "Brave New World", "Aldous Huxley", 450,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam imperdiet nunc ultricies justo commodo, a finibus mauris lacinia. Phasellus et bibendum erat, vel facilisis arcu.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam imperdiet nunc ultricies justo commodo, a finibus mauris lacinia. Phasellus et bibendum erat, vel facilisis arcu. Pellentesque fermentum dui ligula, vel pharetra ex consequat sed. Sed tempus rutrum ultrices. Cras commodo velit vitae arcu iaculis, quis aliquet ipsum mattis. Phasellus sit amet enim urna. Duis nec malesuada turpis. Pellentesque ac purus dolor. Cras et scelerisque massa. Donec sed leo bibendum, finibus nibh vitae, feugiat massa. Nulla tempor massa libero, ut fringilla massa tristique ullamcorper. Vestibulum pellentesque massa semper, suscipit orci pulvinar, aliquet velit. Aenean dictum faucibus pulvinar. Etiam sit amet finibus elit. Vivamus tempus justo sed porttitor fermentum.\n" +
                "\n" +
                "Nullam efficitur lobortis rhoncus. Ut vel quam facilisis, consectetur tellus at, dignissim quam. Ut ullamcorper mi eget dui laoreet vestibulum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec quis turpis augue. Quisque hendrerit felis id turpis aliquet porta. Morbi eget ullamcorper ipsum.\n" +
                "\n" +
                "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec laoreet nisl sed ex accumsan eleifend. Maecenas vel ipsum turpis. Phasellus maximus eros orci, quis laoreet eros interdum faucibus. Aenean arcu turpis, malesuada id ex a, elementum semper lorem. Fusce libero sapien, scelerisque sed felis sit amet, suscipit volutpat nisl. Donec laoreet, nisl sit amet dignissim mollis, orci felis cursus risus, at semper lacus est at lacus. Curabitur dapibus tempor elementum.",
                "https://upload.wikimedia.org/wikipedia/en/6/62/BraveNewWorld_FirstEdition.jpg"));
        books.add(new Book(3L, "Thus Spake Zarathustra", "Friedrich Nietzsche", 400,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam imperdiet nunc ultricies justo commodo, a finibus mauris lacinia. Phasellus et bibendum erat, vel facilisis arcu.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam imperdiet nunc ultricies justo commodo, a finibus mauris lacinia. Phasellus et bibendum erat, vel facilisis arcu. Pellentesque fermentum dui ligula, vel pharetra ex consequat sed. Sed tempus rutrum ultrices. Cras commodo velit vitae arcu iaculis, quis aliquet ipsum mattis. Phasellus sit amet enim urna. Duis nec malesuada turpis. Pellentesque ac purus dolor. Cras et scelerisque massa. Donec sed leo bibendum, finibus nibh vitae, feugiat massa. Nulla tempor massa libero, ut fringilla massa tristique ullamcorper. Vestibulum pellentesque massa semper, suscipit orci pulvinar, aliquet velit. Aenean dictum faucibus pulvinar. Etiam sit amet finibus elit. Vivamus tempus justo sed porttitor fermentum.\n" +
                "\n" +
                "Nullam efficitur lobortis rhoncus. Ut vel quam facilisis, consectetur tellus at, dignissim quam. Ut ullamcorper mi eget dui laoreet vestibulum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec quis turpis augue. Quisque hendrerit felis id turpis aliquet porta. Morbi eget ullamcorper ipsum.\n" +
                "\n" +
                "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec laoreet nisl sed ex accumsan eleifend. Maecenas vel ipsum turpis. Phasellus maximus eros orci, quis laoreet eros interdum faucibus. Aenean arcu turpis, malesuada id ex a, elementum semper lorem. Fusce libero sapien, scelerisque sed felis sit amet, suscipit volutpat nisl. Donec laoreet, nisl sit amet dignissim mollis, orci felis cursus risus, at semper lacus est at lacus. Curabitur dapibus tempor elementum.",
                "https://kbimages1-a.akamaihd.net/68ab0628-2aae-4b78-a4b1-78ea6cacbf62/1200/1200/False/thus-spake-zarathustra-a-book-for-all-and-none-1.jpg"));
        books.add(new Book(4L, "Das Kapital", "Karl Marx", 500,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam imperdiet nunc ultricies justo commodo, a finibus mauris lacinia. Phasellus et bibendum erat, vel facilisis arcu.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam imperdiet nunc ultricies justo commodo, a finibus mauris lacinia. Phasellus et bibendum erat, vel facilisis arcu. Pellentesque fermentum dui ligula, vel pharetra ex consequat sed. Sed tempus rutrum ultrices. Cras commodo velit vitae arcu iaculis, quis aliquet ipsum mattis. Phasellus sit amet enim urna. Duis nec malesuada turpis. Pellentesque ac purus dolor. Cras et scelerisque massa. Donec sed leo bibendum, finibus nibh vitae, feugiat massa. Nulla tempor massa libero, ut fringilla massa tristique ullamcorper. Vestibulum pellentesque massa semper, suscipit orci pulvinar, aliquet velit. Aenean dictum faucibus pulvinar. Etiam sit amet finibus elit. Vivamus tempus justo sed porttitor fermentum.\n" +
                "\n" +
                "Nullam efficitur lobortis rhoncus. Ut vel quam facilisis, consectetur tellus at, dignissim quam. Ut ullamcorper mi eget dui laoreet vestibulum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec quis turpis augue. Quisque hendrerit felis id turpis aliquet porta. Morbi eget ullamcorper ipsum.\n" +
                "\n" +
                "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec laoreet nisl sed ex accumsan eleifend. Maecenas vel ipsum turpis. Phasellus maximus eros orci, quis laoreet eros interdum faucibus. Aenean arcu turpis, malesuada id ex a, elementum semper lorem. Fusce libero sapien, scelerisque sed felis sit amet, suscipit volutpat nisl. Donec laoreet, nisl sit amet dignissim mollis, orci felis cursus risus, at semper lacus est at lacus. Curabitur dapibus tempor elementum.",
                "https://upload.wikimedia.org/wikipedia/commons/8/8d/Zentralbibliothek_Z%C3%BCrich_Das_Kapital_Marx_1867.jpg"));
        books.add(new Book(5L, "The Lord of the Rings", "J.R.R. Tolkien", 700,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam imperdiet nunc ultricies justo commodo, a finibus mauris lacinia. Phasellus et bibendum erat, vel facilisis arcu.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam imperdiet nunc ultricies justo commodo, a finibus mauris lacinia. Phasellus et bibendum erat, vel facilisis arcu. Pellentesque fermentum dui ligula, vel pharetra ex consequat sed. Sed tempus rutrum ultrices. Cras commodo velit vitae arcu iaculis, quis aliquet ipsum mattis. Phasellus sit amet enim urna. Duis nec malesuada turpis. Pellentesque ac purus dolor. Cras et scelerisque massa. Donec sed leo bibendum, finibus nibh vitae, feugiat massa. Nulla tempor massa libero, ut fringilla massa tristique ullamcorper. Vestibulum pellentesque massa semper, suscipit orci pulvinar, aliquet velit. Aenean dictum faucibus pulvinar. Etiam sit amet finibus elit. Vivamus tempus justo sed porttitor fermentum.\n" +
                "\n" +
                "Nullam efficitur lobortis rhoncus. Ut vel quam facilisis, consectetur tellus at, dignissim quam. Ut ullamcorper mi eget dui laoreet vestibulum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec quis turpis augue. Quisque hendrerit felis id turpis aliquet porta. Morbi eget ullamcorper ipsum.\n" +
                "\n" +
                "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec laoreet nisl sed ex accumsan eleifend. Maecenas vel ipsum turpis. Phasellus maximus eros orci, quis laoreet eros interdum faucibus. Aenean arcu turpis, malesuada id ex a, elementum semper lorem. Fusce libero sapien, scelerisque sed felis sit amet, suscipit volutpat nisl. Donec laoreet, nisl sit amet dignissim mollis, orci felis cursus risus, at semper lacus est at lacus. Curabitur dapibus tempor elementum.",
                "https://cdn.ozon.gr/media/catalog/product/cache/1/image/400x498/a4e40ebdc3e371adff845072e1c73f37/t/h/7361ea7128cbb179b6a2d512437f63f2/the-lord-of-the-rings--book-1--the-fellowship-of-the-ring-tv-series-tie-in-a-30.jpg"));

        List<BookUser> bookUserCorrelations = new ArrayList<>();

        save(USER_SOURCE, users);
        save(BOOK_SOURCE, books);
        save(USER_BOOK_SOURCE, bookUserCorrelations);
    }

    public List<User> getUsers() {
        Type type = new TypeToken<List<User>>() {
        }.getType();
        return gson.fromJson(sharedPreferences.getString(USER_SOURCE, null), type);
    }

    public List<Book> getBooks() {
        Type type = new TypeToken<List<Book>>() {
        }.getType();
        return gson.fromJson(sharedPreferences.getString(BOOK_SOURCE, null), type);
    }

    public List<BookUser> getBookUserCorrelations() {
        Type type = new TypeToken<List<BookUser>>() {
        }.getType();
        return gson.fromJson(sharedPreferences.getString(USER_BOOK_SOURCE, null), type);
    }

    public User getCurrentUser() {
        Type type = new TypeToken<User>() {
        }.getType();
        return gson.fromJson(sharedPreferences.getString(CURRENT_USER_SOURCE, null), type);
    }

    private void save(String key, Object value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, gson.toJson(value));
        editor.commit();
    }

    private void delete(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public void register(User user, ProgressBar progressBar) {
        List<User> users = getUsers();
        users.add(user);
        save(USER_SOURCE, users);
        for (int i = 0; i < 10; i++) {
            SystemClock.sleep(300);
            progressBar.incrementProgressBy(i + 10);
        }
        progressBar.setProgress(0);
    }

    public boolean login(User user, ProgressBar progressBar) {
        for (int i = 0; i < 10; i++) {
            SystemClock.sleep(300);
            progressBar.incrementProgressBy(i + 10);
        }
        progressBar.setProgress(0);
        List<User> users = getUsers();
        for (User existingUser : users) {
            if (existingUser.getEmail().equals(user.getEmail()) && existingUser.getPassword().equals(user.getPassword())) {
                save(CURRENT_USER_SOURCE, existingUser);
                return true;
            }
        }
        return false;
    }

    public void logout() {
        delete(CURRENT_USER_SOURCE);
    }

    private User getUser(String email) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public Book getBook(Long id) {
        List<Book> books = getBooks();
        for (Book book : books) {
            if (Objects.equals(id, book.getId())) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getFavoriteBooks(String userEmail) {
        List<BookUser> bookUserCorrelations = getBookUserCorrelations();
        List<Book> favoriteBooks = new ArrayList<>();
        for (BookUser correlation : bookUserCorrelations) {
            if (correlation.getUser().getEmail().equals(userEmail) && correlation.isFavorite()) {
                favoriteBooks.add(correlation.getBook());
            }
        }
        return favoriteBooks;
    }

    public List<Book> getWishlistBooks(String userEmail) {
        List<BookUser> bookUserCorrelations = getBookUserCorrelations();
        List<Book> wishlistBooks = new ArrayList<>();
        for (BookUser correlation : bookUserCorrelations) {
            if (correlation.getUser().getEmail().equals(userEmail) && correlation.isWishlist()) {
                wishlistBooks.add(correlation.getBook());
            }
        }
        return wishlistBooks;
    }

    public List<Book> getAlreadyReadBooks(String userEmail) {
        List<BookUser> bookUserCorrelations = getBookUserCorrelations();
        List<Book> alreadyReadBooks = new ArrayList<>();
        for (BookUser correlation : bookUserCorrelations) {
            if (correlation.getUser().getEmail().equals(userEmail) && correlation.isAlreadyRead()) {
                alreadyReadBooks.add(correlation.getBook());
            }
        }
        return alreadyReadBooks;
    }

    public List<Book> getCurrentlyReadingBooks(String userEmail) {
        List<BookUser> bookUserCorrelations = getBookUserCorrelations();
        List<Book> currentlyReadingBooks = new ArrayList<>();
        for (BookUser correlation : bookUserCorrelations) {
            if (correlation.getUser().getEmail().equals(userEmail) && correlation.isCurrentlyReading()) {
                currentlyReadingBooks.add(correlation.getBook());
            }
        }
        return currentlyReadingBooks;
    }

    public BookUser getBookUserCorrelation(String userEmail, Long bookId) {
        List<BookUser> bookUserCorrelations = getBookUserCorrelations();
        for (BookUser bookUser : bookUserCorrelations) {
            if (bookUser.getUser().getEmail().equals(userEmail) && bookUser.getBook().getId().equals(bookId)) {
                return bookUser;
            }
        }
        BookUser bookUser = new BookUser(getUser(userEmail), getBook(bookId));
        bookUserCorrelations.add(bookUser);
        save(USER_BOOK_SOURCE, bookUserCorrelations);
        return bookUser;
    }

    public void updateBookUserCorrelation(BookUser bookUser) {
        List<BookUser> bookUserCorrelations = getBookUserCorrelations();
        String userEmail = bookUser.getUser().getEmail();
        Long bookId = bookUser.getBook().getId();
        for (BookUser existingBookUser : bookUserCorrelations) {
            if (existingBookUser.getUser().getEmail().equals(userEmail) && existingBookUser.getBook().getId().equals(bookId)) {
                existingBookUser.setFavorite(bookUser.isFavorite());
                existingBookUser.setWishlist(bookUser.isWishlist());
                existingBookUser.setAlreadyRead(bookUser.isAlreadyRead());
                existingBookUser.setCurrentlyReading(bookUser.isCurrentlyReading());
                save(USER_BOOK_SOURCE, bookUserCorrelations);
                break;
            }
        }
    }
}
