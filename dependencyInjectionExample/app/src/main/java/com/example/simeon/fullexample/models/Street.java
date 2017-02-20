package com.example.simeon.fullexample.models;

import javax.inject.Inject;
import javax.inject.Named;

public class Street {
    private final String streetName;
    private final Class<Book> classBook;

    @Inject
    public Street(@Named("streetName") String streetName, Class<Book> classBook) {
        this.streetName = streetName;
        this.classBook = classBook;
    }

    public String getStreetName() {
        return streetName;
    }

    public Class<Book> getClassBook() {
        return classBook;
    }
}
