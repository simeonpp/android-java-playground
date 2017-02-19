package com.example.simeon.firstapplication.activities.httpRequests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.fragments.httpRequests.LoadingFragment;
import com.example.simeon.firstapplication.models.Book;
import com.example.simeon.firstapplication.tasks.HttpGetAsyncTask;

public class BookDetailsHttpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details_http);

        this.initBookDetails();
    }

    private void initBookDetails() {
        LoadingFragment loadingFragment = LoadingFragment.create(this);
        loadingFragment.show();

        Intent intent = this.getIntent();
        Book intentBook = (Book) intent.getSerializableExtra("book");

        new HttpGetAsyncTask<Book>(Book.class, (book) -> {
            runOnUiThread(() -> {
                this.setViewBookDetails(book);
                loadingFragment.hide();
            });
        }).execute("http://192.168.0.103:8000/api/book/" + intentBook.getISBN());
    }

    private void setViewBookDetails(Book book) {
        TextView tvISBN = (TextView) this.findViewById(R.id.tv_book_http_ISBN);
        tvISBN.setText(book.getISBN());

        TextView tvTitle = (TextView) this.findViewById(R.id.tv_book_http_title);
        tvTitle.setText(book.getTitle());
    }
}
