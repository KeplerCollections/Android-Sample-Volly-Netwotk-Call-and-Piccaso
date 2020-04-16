package com.kepler.codechallenge;


import android.os.Bundle;
import android.os.Handler;

import com.kepler.codechallenge.boilers.BaseActivity;

public class SplashScreen extends BaseActivity {


    private static final long DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.class);
                finish();
            }
        },DELAY);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected int getContainerId() {
        return 0;
    }
}
