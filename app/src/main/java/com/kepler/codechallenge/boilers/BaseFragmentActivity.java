package com.kepler.codechallenge.boilers;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kepler.codechallenge.api.VolleyController;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragmentActivity extends BaseActivity {


    protected void replaceFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(getContainerId(), fragment).commit();
    }

    protected void replaceFragment(BaseFragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(getContainerId(), fragment).addToBackStack(tag).commit();
    }

    protected abstract int getContainerId();

}
