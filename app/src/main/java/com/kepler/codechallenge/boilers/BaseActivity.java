package com.kepler.codechallenge.boilers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kepler.codechallenge.api.VolleyController;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private ConnectivityManager cm;

    //in your Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

    }

    @Override
    protected void onDestroy() {
        VolleyController.destroy();
        super.onDestroy();
    }

    protected abstract int getContentView();

    protected boolean isConnected() {
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }


    protected void replaceFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(getContainerId(), fragment).commit();
    }

    protected void addFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().add(getContainerId(), fragment).addToBackStack(null).commit();
    }

    protected abstract int getContainerId();

    protected void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
