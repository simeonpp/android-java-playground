package com.example.simeon.firstapplication.activities.navigation.object;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.models.Book;

public class NavigationObjectPage1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_object_page1);

        Button button = (Button) this.findViewById(R.id.btn_navigation_object);
        button.setOnClickListener(view -> {
            EditText etISBN = (EditText) this.findViewById(R.id.et_book_isbn);
            EditText etTitle = (EditText) this.findViewById(R.id.et_book_title);

            Book book = new Book(etISBN.getText().toString(), etTitle.getText().toString());

            Intent intent = new Intent(this, NavigationObjectPage2Activity.class);
            intent.putExtra(NavigationObjectPage2Activity.BOOK_KEY, book);

            this.startActivity(intent);
        });
    }

}
