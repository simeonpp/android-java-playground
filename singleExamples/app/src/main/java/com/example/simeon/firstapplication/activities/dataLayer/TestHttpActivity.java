package com.example.simeon.firstapplication.activities.dataLayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.data.base.IData;
import com.example.data.services.HttpRestData;
import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.models.Book;

public class TestHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_http);

        String url = "http://192.168.0.103:8000/api/book";

        IData<Book> bookData = new HttpRestData<Book>(url, Book.class, Book[].class);
        Book newBook = new Book("7", "Harry Potter", "asdsd");
        bookData.add(newBook)
            .switchMap(book -> bookData.getAll())
            .subscribe(books -> {
                StringBuilder stringBuilder = new StringBuilder();

                for(Book book: books) {
                    stringBuilder.append(book.getTitle());
                    stringBuilder.append(",");
                }

                Toast
                    .makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG)
                    .show();
            });
    }
}
