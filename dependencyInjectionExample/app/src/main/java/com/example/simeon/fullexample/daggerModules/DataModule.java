package com.example.simeon.fullexample.daggerModules;

import com.example.simeon.fullexample.models.ApiUrl;
import com.example.simeon.fullexample.models.Book;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    Book provideBook() {
        return  new Book("123123-123123", "Book title", "http://www.h");
    }

    @Provides
    Class<Book> provideBookType() {
        return Book.class;
    }

    @Provides
    ApiUrl<Book> provideBookApiUrl(@Named("apiBaseUrl") String apiBaseUrl) {
        ApiUrl<Book> apiUrlBook = new ApiUrl<>(apiBaseUrl, "books");
        return apiUrlBook;
    }
}
