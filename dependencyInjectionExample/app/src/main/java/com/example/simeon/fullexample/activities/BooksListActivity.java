package com.example.simeon.fullexample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.simeon.fullexample.MyApplicationApplication;
import com.example.simeon.fullexample.R;
import com.example.simeon.fullexample.TempData;
import com.example.simeon.fullexample.models.ApiUrl;
import com.example.simeon.fullexample.models.Book;
import com.example.simeon.fullexample.models.Street;

import javax.inject.Inject;

public class BooksListActivity extends AppCompatActivity {

    @Inject
    TempData tempData;

    @Inject
    Street street;

    @Inject
    ApiUrl<Book> apiBookUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);

        this.injectDependencies();

        // First injection test example
        Book book = tempData.getBook();
        Toast
            .makeText(this, book.getTitle(), Toast.LENGTH_LONG)
            .show();

        // Second injection test example
        Log.d("I", "--------------------------------------");

        Log.d("I", street.getStreetName());
        Log.d("I", street.getClassBook().toString());

        Log.d("I", apiBookUrl.getUrl());

        Log.d("I", "--------------------------------------");
    }

    private void injectDependencies() {
        ((MyApplicationApplication) getApplication())
            .getComponent()
            .inject(this);
    }
}
