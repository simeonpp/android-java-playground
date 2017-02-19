package com.example.simeon.firstapplication.activities.httpRequests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.fragments.httpRequests.LoadingFragment;
import com.example.simeon.firstapplication.models.Book;
import com.example.simeon.firstapplication.tasks.HttpPostAsyncTask;

public class CreateBookHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book_http);

        this.setCreateBookButtonListener();
    }

    private void setCreateBookButtonListener() {
        Button btnCreateBook = (Button) this.findViewById(R.id.btn_add_book_http);
        btnCreateBook.setOnClickListener((v -> {
            LoadingFragment loadingFragment = LoadingFragment.create(this);
            loadingFragment.show();

            // NO VALIDATION IS PERFORMED
            EditText etISBN = (EditText) this.findViewById(R.id.et_add_book_http_isbn);
            String isbn = etISBN.getText().toString();

            EditText etTitle = (EditText) this.findViewById(R.id.et_add_book_http_title);
            String title = etTitle.getText().toString();

            Book book = new Book(isbn, title);
            new HttpPostAsyncTask<Book>(Book.class, book, (bookAdded) -> {
                Toast
                    .makeText(this, String.format("Book added: %s", bookAdded.getTitle()), Toast.LENGTH_LONG)
                    .show();

                loadingFragment.hide();

                Intent intent = new Intent(this, ListBooksHttpActivity.class);
                this.startActivity(intent);
            }).execute("http://192.168.0.103:8000/api/book");
        }));
    }
}
