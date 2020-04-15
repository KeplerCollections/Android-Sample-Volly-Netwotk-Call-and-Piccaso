package com.kepler.codechallenge.api;


public interface VolleyCallback<T> {

    void onResponse(T response);

    void onError(Throwable errorMessage);

}