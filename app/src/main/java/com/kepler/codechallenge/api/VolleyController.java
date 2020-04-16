package com.kepler.codechallenge.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VolleyController extends RequestCall {
    private static VolleyController mInstance;
    private static Context mCtx;

    private VolleyController(Context context) {
        mCtx = context;
        init();
    }

    public static synchronized VolleyController getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyController(context);
        }
        return mInstance;
    }

    public static void destroy() {
        mInstance = null;
        mCtx = null;
    }


    @Override
    protected RequestQueue initRequestQueue() {
        return Volley.newRequestQueue(mCtx);
    }

}