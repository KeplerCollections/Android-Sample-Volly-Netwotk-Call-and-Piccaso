package com.kepler.codechallenge.boilers;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kepler.codechallenge.R;
import com.kepler.codechallenge.api.VolleyController;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private ConnectivityManager cm;
    private Unbinder unbinder;

    //in your Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        unbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    protected void onDestroy() {
        VolleyController.destroy();
        unbinder.unbind();
        super.onDestroy();
    }

    protected abstract int getContentView();

    protected boolean isConnected() {
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void enableBackButton() {
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void disableBackButton() {
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    protected void replaceFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(getContainerId(), fragment).commit();
    }

    protected void replaceFragment(BaseFragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(getContainerId(), fragment).addToBackStack(null).commit();
    }

    protected void addFragment(BaseFragment fragment,String backStack) {
        getSupportFragmentManager().beginTransaction().add(getContainerId(), fragment).addToBackStack(null).commit();
    }

    protected void addFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().add(getContainerId(), fragment).commit();
    }

    protected void startActivity(Class baseActivity) {
        startActivity(new Intent(this, baseActivity));

    }


    protected abstract int getContainerId();

    protected void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
