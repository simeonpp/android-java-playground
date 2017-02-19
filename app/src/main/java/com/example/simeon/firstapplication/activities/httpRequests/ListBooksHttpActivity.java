package com.example.simeon.firstapplication.activities.httpRequests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.fragments.httpRequests.LoadingFragment;
import com.example.simeon.firstapplication.models.Book;
import com.example.simeon.firstapplication.tasks.HttpGetAsyncTask;

import java.util.ArrayList;
import java.util.Arrays;

public class ListBooksHttpActivity extends AppCompatActivity {

    private Book[] books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books_http);

        this.initBooksListView();
        this.addCreateBookButtonListener();
    }

    private void initBooksListView() {
        ListView lvBooksHttp = (ListView) this.findViewById(R.id.lv_books_http);
        // Set up list adapter
        ArrayAdapter<String> bookAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        lvBooksHttp.setAdapter(bookAdapter);

        // Attach on item click event
        lvBooksHttp.setOnItemClickListener(((parent, view, position, id) -> {
            Book book = books[position];

            Intent intent = new Intent(this, BookDetailsHttpActivity.class);
            intent.putExtra("book", book);

            this.startActivity(intent);
        }));

        // Perform Ajax HTTP Request
        this.loadBooks(bookAdapter);
    }

    private void loadBooks(ArrayAdapter<String> bookAdapter) {
        LoadingFragment loadingFragment = LoadingFragment.create(this);
        loadingFragment.show();

        new HttpGetAsyncTask<Book[]>(Book[].class, (books) -> {
            this.books = books;
//            ArrayList<Book> booksAsCollection = new ArrayList<Book>(Arrays.asList(books));

            runOnUiThread(() -> {
//                bookAdapter.addAll(booksAsCollection); // Because we have string adapter
                Arrays.stream(books)
                    .forEach((book) -> {
                        bookAdapter.add(book.getTitle());
                    });
                loadingFragment.hide();
            });
        }).execute("http://192.168.0.103:8000/api/books");
    }

    private void addCreateBookButtonListener() {
        Button addNewBookButton = (Button) this.findViewById(R.id.btn_add_new_http_book);
        addNewBookButton.setOnClickListener((v -> {
            Intent intent = new Intent(this, CreateBookHttpActivity.class);
            this.startActivity(intent);
        }));
    }
}
