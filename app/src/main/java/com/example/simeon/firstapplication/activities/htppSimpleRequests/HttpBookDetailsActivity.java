package com.example.simeon.firstapplication.activities.htppSimpleRequests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.models.Book;

public class HttpBookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_book_details);

        Intent intent = this.getIntent();
        Book book = (Book) intent.getSerializableExtra("book");

        TextView tvBookTitle = (TextView) this.findViewById(R.id.tv_http_details_book);
        tvBookTitle.setText(book.getTitle());
    }
}
