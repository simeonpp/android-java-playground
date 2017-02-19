package com.example.simeon.firstapplication.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class HttpAsyncTask<T> extends AsyncTask<String, String, T> {

    public interface OnRequestFinished<T> {
        void onRequestFinished(T data);
    }

    private final Class<T> klass;
    private final OnRequestFinished<T> onRequestFinished;
    private final OkHttpClient httpClient;

    public HttpAsyncTask(Class<T> klass, OnRequestFinished<T> onRequestFinished) {
        this.klass = klass;
        this.onRequestFinished = onRequestFinished;

        this.httpClient = new OkHttpClient();
    }

    protected Class<T> getKlass() {
        return this.klass;
    }

    @Override
    protected T doInBackground(String... params) {
        String url = params[0];

        Request request = this.buildRequest(url);

        try {
            Response response = this.httpClient.newCall(request).execute();
            String responseBodyAsJson = response.body().string();
            Gson gson = new Gson();
            T data = gson.fromJson(responseBodyAsJson, this.getKlass());
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected abstract Request buildRequest(String url);

    @Override
    protected void onPostExecute(T data) {
        this.onRequestFinished.onRequestFinished(data);
    }
}
