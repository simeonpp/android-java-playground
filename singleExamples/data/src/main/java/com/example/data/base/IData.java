package com.example.data.base;

import io.reactivex.Observable;

public interface IData<T> {
    Observable<T[]> getAll();
    Observable<T> getById(Object id); // id can be string or integer
    Observable<T> add(T object);
}
