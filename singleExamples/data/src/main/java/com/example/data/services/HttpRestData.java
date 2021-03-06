package com.example.data.services;

import com.example.data.base.IData;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRestData<T> implements IData<T> {
    private final String url;
    private final Class<T> klassSingle;
    private final Class<T[]> klassArray;
    private final OkHttpClient httpClient;

    public HttpRestData(String url, Class<T> klassSingle, Class<T[]> klassArray) {
        this.url = url;
        this.klassSingle = klassSingle;
        this.klassArray = klassArray;

        this.httpClient = new OkHttpClient();
    }

    public Observable<T[]> getAll() {
        return Observable
            .create(new ObservableOnSubscribe<T[]>() {
                @Override
                public void subscribe(ObservableEmitter<T[]> e) throws Exception {
                    Request request = buildGetRequest(url + "s");
                    Response response = httpClient.newCall(request).execute();

                    T[] objects = parseArray(response.body().string());
                    e.onNext(objects);
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<T> getById(Object id) { // id can be string or integer
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                Request request = buildGetRequest(url + "/" + id);
                Response response = httpClient.newCall(request).execute();

                T object = parseSingle(response.body().string());
                e.onNext(object);
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<T> add(T object) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                Request request = buildRequestWithBody(object, url);
                Response response = httpClient.newCall(request).execute();

                T resultObject = parseSingle(response.body().string());
                e.onNext(resultObject);
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
    }

    private Request buildGetRequest(String url) {
        return
            new Request.Builder()
                .url(url)
                .build();
    }

    private Request buildRequestWithBody(T object, String url) {
        return this.buildRequestWithBody(RequestWithBodyType.POST, object, url);
    }

    private Request buildRequestWithBody(RequestWithBodyType type, T object, String url) {
        Gson gson = new Gson();
        String jsonBody = gson.toJson(object);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);

        Request.Builder requestBuilder = new Request.Builder()
                .url(url);

        switch (type){
            case POST:
                requestBuilder = requestBuilder.post(requestBody);
                break;
            case PUT:
                requestBuilder = requestBuilder.put(requestBody);
                break;
            case DELETE:
                requestBuilder = requestBuilder.delete(requestBody);
                break;
        }

        Request request = requestBuilder.build();
        return request;
    }

    private T[] parseArray(String string) {
        Gson gson = new Gson();
        return gson.fromJson(string, this.klassArray);
    }

    private T parseSingle(String string) {
        Gson gson = new Gson();
        return  gson.fromJson(string, this.klassSingle);
    }

    enum RequestWithBodyType {
        POST,
        PUT,
        DELETE
    }
}
