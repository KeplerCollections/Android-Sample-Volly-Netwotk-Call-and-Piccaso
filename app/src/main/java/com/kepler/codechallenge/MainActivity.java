package com.kepler.codechallenge;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.kepler.codechallenge.boilers.BaseFragment;
import com.kepler.codechallenge.boilers.MVPActivity;
import com.kepler.codechallenge.support.interfaces.MainFragmentCommunicator;
import com.kepler.codechallenge.presenters.AppLogic;
import com.kepler.codechallenge.presenters.MainPresnter;
import com.mapbox.mapboxsdk.Mapbox;

import butterknife.BindView;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

import static com.kepler.codechallenge.api.RequestCall.EP_DELIVERIES;

public class MainActivity extends MVPActivity<AppLogic.MainLogic> implements AppLogic.MainView, MainFragmentCommunicator {

    @BindView(R.id.progress)
    ProgressBar progressBar;
    @Override
    protected AppLogic.MainLogic createPresenter() {
        return new MainPresnter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1Ijoia2VwbGVyY29sbGVjdGlvbnMiLCJhIjoiY2s5MmJhY3BlMDJ0YzNtcGdkaWJxZmhxaSJ9.eIb6XqN0xDtKhuPPw3jxmg");
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

    @Override
    public void startProgressing() {
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgressing() {
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);
    }
}
