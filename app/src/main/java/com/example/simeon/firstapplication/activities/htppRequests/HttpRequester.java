package com.example.simeon.firstapplication.activities.htppRequests;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.simeon.firstapplication.R;
import com.example.simeon.firstapplication.models.Book;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpRequester extends AppCompatActivity {
    private Book[] books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_requester);

        // Adding progress loading bar
        ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage("Please wait...");
        mDialog.setCancelable(false);
        mDialog.show();

        // Set up list adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        ListView lvBooks = (ListView) this.findViewById(R.id.lv_books_ajax);
        lvBooks.setOnItemClickListener(((parent, view, position, id) -> {
            Book currentBook = this.books[position];

            Intent intent = new Intent(this, HttpBookDetailsActivity.class);
            intent.putExtra("book", currentBook);
            this.startActivity(intent);
        }));
        lvBooks.setAdapter(adapter);

        // AJAX HTTP request
        new PerformHttpAsyncTask((Book[] books) -> {
            this.runOnUiThread(() -> {
//                Toast
//                    .makeText(this, result, Toast.LENGTH_LONG)
//                    .show();
//                Log.d("LOG", result);
                this.books = books;
                Arrays.stream(books)
                    .forEach((book) -> {
                        adapter.add(book.getTitle());
                    });
                mDialog.hide();

            });
        }).execute("http://192.168.0.103:8000/api/books");
    }

    class PerformHttpAsyncTask extends AsyncTask<String, String, String> {
        private final OkHttpClient okHttpClient;
        private final OnPostExecutedFinished onPostExecuteFinished;

        public PerformHttpAsyncTask(OnPostExecutedFinished onPostExecuteFinished) {
            this.okHttpClient = new OkHttpClient();
            this.onPostExecuteFinished = onPostExecuteFinished;
        }

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try {
                Response response = this.okHttpClient
                        .newCall(request)
                        .execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String jsonBody) {
            super.onPostExecute(jsonBody);

            Gson gson = new Gson();
            Book[] books = gson.fromJson(jsonBody, Book[].class);

            this.onPostExecuteFinished.call(books);
        }
    }

    public interface OnPostExecutedFinished {
        void call(Book[] books);
    }
}
