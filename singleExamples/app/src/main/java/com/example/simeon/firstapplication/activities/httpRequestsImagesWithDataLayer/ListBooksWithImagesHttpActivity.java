package com.example.simeon.firstapplication.activities.httpRequestsImagesWithDataLayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.data.base.IData;
import com.example.data.services.HttpRestData;
import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.fragments.httpRequests.LoadingFragment;
import com.example.simeon.firstapplication.models.Book;

import java.util.ArrayList;
import java.util.Arrays;

public class ListBooksWithImagesHttpActivity extends AppCompatActivity {
    private final String url = "http://192.168.0.103:8000/api/book";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books_with_images_http);

        this.initBooksListView();
    }

    private void initBooksListView() {
        ListView lvBooksHttp = (ListView) this.findViewById(R.id.lv_books_with_images);

        // Set up list adapter
        ArrayAdapter<Book> bookAdapter = new ImageTextArrayAdapter(this);
        lvBooksHttp.setAdapter(bookAdapter);

        // Perform Ajax HTTP Request
        this.loadBooks(bookAdapter);
    }

    private void loadBooks(ArrayAdapter<Book> bookAdapter) {
        LoadingFragment loadingFragment = LoadingFragment.create(this);
        loadingFragment.show();

        IData<Book> bookData = new HttpRestData<Book>(url, Book.class, Book[].class);
        bookData.getAll()
            .subscribe(books -> {
                bookAdapter.clear();
                bookAdapter.addAll(books);
                loadingFragment.hide();
            });
    }
}
