package com.example.simeon.fullexample.models;

import javax.inject.Inject;

public class Book {
    private String ISBN;
    private String title;
    private String imageUrl;

    @Inject
    public Book(String ISBN, String title, String imageUrl) {
        this.ISBN = ISBN;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
