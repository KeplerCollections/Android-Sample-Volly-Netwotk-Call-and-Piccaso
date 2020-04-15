package com.kepler.codechallenge;

import android.os.Bundle;

import com.kepler.codechallenge.boilers.BaseFragment;
import com.kepler.codechallenge.boilers.MVPActivity;
import com.kepler.codechallenge.support.interfaces.MainFragmentCommunicator;
import com.kepler.codechallenge.presenters.AppLogic;
import com.kepler.codechallenge.presenters.MainPresnter;

import io.reactivex.rxjava3.observers.DisposableSingleObserver;

import static com.kepler.codechallenge.api.RequestCall.EP_DELIVERIES;

public class MainActivity extends MVPActivity<AppLogic.MainLogic> implements AppLogic.MainView, MainFragmentCommunicator {


    @Override
    protected AppLogic.MainLogic createPresenter() {
        return new MainPresnter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(new FragmentListOfDeliveries());
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
    public void addNewFragment(BaseFragment baseFragment) {

    }

    @Override
    public void replaceNewFragment(BaseFragment baseFragment) {
        replaceFragment(baseFragment,null);
    }

    @Override
    public void handleBackButton(boolean enable) {
        if (enable) {
            enableBackButton();
        } else {
            disableBackButton();
        }
    }


    @Override
    public void loadDeleiveries(DisposableSingleObserver disposableSingleObserver) {
        presenter.loadData(EP_DELIVERIES, disposableSingleObserver);
    }
}
