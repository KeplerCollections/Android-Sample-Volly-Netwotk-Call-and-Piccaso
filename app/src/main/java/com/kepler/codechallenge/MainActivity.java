package com.kepler.codechallenge;

import android.os.Bundle;
import android.os.Handler;

import com.kepler.codechallenge.api.VolleyCallback;
import com.kepler.codechallenge.boilers.MVPActivity;
import com.kepler.codechallenge.boilers.MainFragmentCommunicator;
import com.kepler.codechallenge.presenters.AppLogic;
import com.kepler.codechallenge.presenters.MainPresnter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.kepler.codechallenge.api.RequestCall.EP_DELIVERIES;

public class MainActivity extends MVPActivity<AppLogic.MainLogic> implements AppLogic.MainView, MainFragmentCommunicator {


    @Override
    protected AppLogic.MainLogic createPresenter() {
        return new MainPresnter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(new FragmentListOfDeliveries());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected int getContainerId() {
        return R.id.container;
    }

    @Override
    public boolean isNetworkAvailble() {
        return isConnected();
    }

    @Override
    public void setFragmentTitle(int title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void toast(String msg) {
        toastMessage(msg);
    }

    @Override
    protected void onStop() {
        presenter.onStopCalled();
        super.onStop();
    }

    @Override
    public void loadDeleiveries(DisposableSingleObserver disposableSingleObserver) {
        addFragment(new FragmentDeliveryDetail());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.loadData(EP_DELIVERIES,disposableSingleObserver);

            }
        },5000);
    }
}
