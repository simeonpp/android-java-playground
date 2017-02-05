package com.example.simeon.firstapplication.activities.navigation.object;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.models.Book;

public class NavigationObjectPage2Activity extends Activity {

    public static final String BOOK_KEY = "book_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_object_page2);

        TextView tvBookISBN = (TextView) this.findViewById(R.id.tv_book_isbn);
        TextView tvBookTitle = (TextView) this.findViewById(R.id.tv_book_title);

        Intent intent = this.getIntent();
        Book book = (Book) intent.getSerializableExtra(NavigationObjectPage2Activity.BOOK_KEY);

        tvBookISBN.setText(book.getISBN());
        tvBookTitle.setText(book.getTitle());
    }

}
