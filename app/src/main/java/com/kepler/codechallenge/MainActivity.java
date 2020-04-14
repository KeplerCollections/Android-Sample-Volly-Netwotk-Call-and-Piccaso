package com.kepler.codechallenge;

import android.os.Bundle;

import com.kepler.codechallenge.boilers.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}
