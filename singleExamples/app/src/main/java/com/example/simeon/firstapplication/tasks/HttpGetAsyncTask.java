package com.example.simeon.firstapplication.tasks;

import okhttp3.Request;

public class HttpGetAsyncTask<T> extends HttpAsyncTask<T> {
    public HttpGetAsyncTask(Class<T> klass, OnRequestFinished<T> onRequestFinished) {
        super(klass, onRequestFinished);
    }

    @Override
    protected Request buildRequest(String url) {
        Request request = new Request.Builder()
            .url(url)
            .build();

        return  request;
    }
}
