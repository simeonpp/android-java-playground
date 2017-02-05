package com.example.simeon.firstapplication.models;

import java.io.Serializable;

public class Book implements Serializable {
    private String ISBN;
    private String title;

    public Book(String ISBN, String title) {
        this.setISBN(ISBN);
        this.setTitle(title);
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
}
