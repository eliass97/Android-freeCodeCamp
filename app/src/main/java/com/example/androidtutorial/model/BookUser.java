package com.example.androidtutorial.model;

public class BookUser {

    private User user;
    private Book book;
    private Boolean isWishlist;
    private Boolean isFavorite;
    private Boolean isCurrentlyReading;
    private Boolean isAlreadyRead;

    public BookUser() {
        this.isWishlist = false;
        this.isFavorite = false;
        this.isCurrentlyReading = false;
        this.isAlreadyRead = false;
    }

    public BookUser(User user, Book book) {
        this();
        this.user = user;
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Boolean isWishlist() {
        return isWishlist;
    }

    public void setWishlist(Boolean wishlist) {
        isWishlist = wishlist;
    }

    public Boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public Boolean isCurrentlyReading() {
        return isCurrentlyReading;
    }

    public void setCurrentlyReading(Boolean currentlyReading) {
        isCurrentlyReading = currentlyReading;
    }

    public Boolean isAlreadyRead() {
        return isAlreadyRead;
    }

    public void setAlreadyRead(Boolean alreadyRead) {
        isAlreadyRead = alreadyRead;
    }
}
