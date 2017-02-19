package com.example.simeon.firstapplication.tasks;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpPostAsyncTask<T> extends HttpAsyncTask<T> {
    private T bodyData;

    public HttpPostAsyncTask(Class<T> klass, T bodyData, OnRequestFinished<T> onRequestFinished) {
        super(klass, onRequestFinished);
        this.bodyData = bodyData;
    }

    @Override
    protected Request buildRequest(String url) {
        Gson gson = new Gson();
        String jsonPostBody = gson.toJson(this.bodyData);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonPostBody);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        return  request;
    }
}
