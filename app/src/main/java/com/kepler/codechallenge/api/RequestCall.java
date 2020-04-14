package com.kepler.codechallenge.api;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

public abstract class RequestCall {

    public static final String BASE = "http://demo3196012.mockable.io/";
    private RequestQueue mRequestQueue;


    public void jsonRequest(String urlAppender, final VolleyCallback volleyCallback) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, BASE + urlAppender, null, response -> volleyCallback.onResponse(response), error -> {
                    // TODO: Handle error
                    volleyCallback.onError(error.getMessage());

                });


// Access the RequestQueue through your singleton class.
    }

    public void stringRequest(String urlAppender, final VolleyCallback volleyCallback) {
        StringRequest stringRequest = new StringRequest
                (Request.Method.GET, BASE + urlAppender, response -> volleyCallback.onResponse(response), error -> {
                    // TODO: Handle error
                    volleyCallback.onError(error.getMessage());

                }) {

        };

        getRequestQueue().add(stringRequest);


// Access the RequestQueue through your singleton class.
    }

    protected RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = initRequestQueue();
        }
        return mRequestQueue;
    }

    protected abstract RequestQueue initRequestQueue();

    protected void cancelAllRequests() {
        getRequestQueue().cancelAll(null);
    }

    protected void addToRequestQueue(Request req) {
        getRequestQueue().add(req);
    }

}