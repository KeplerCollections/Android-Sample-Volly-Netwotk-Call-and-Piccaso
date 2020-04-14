package com.kepler.codechallenge.boilers;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.kepler.codechallenge.api.VolleyController;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity  {

    //in your Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        VolleyController.destroy();
        super.onDestroy();
    }

    protected abstract int getContentView();
}
