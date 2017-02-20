package com.example.simeon.fullexample;

import com.example.simeon.fullexample.models.Book;

import javax.inject.Inject;

public class TempData {
    private Book book;

    @Inject
    public TempData(Book book) {
        this.setBook(book);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
