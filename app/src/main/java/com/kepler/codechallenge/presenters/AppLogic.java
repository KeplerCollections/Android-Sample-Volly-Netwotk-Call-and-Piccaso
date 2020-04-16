package com.kepler.codechallenge.presenters;

import android.content.Context;

import com.kepler.codechallenge.api.VolleyCallback;
import com.kepler.codechallenge.api.VolleyController;
import com.kepler.codechallenge.boilers.MVP;

import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class AppLogic {

    public interface RequiredBaseView extends MVP.BaseView {
        boolean isNetworkAvailble();
        Context getApplicationContext();
    }

    /********* Logic for Main************/
    public interface MainView extends RequiredBaseView {
        void startProgressing();
        void stopProgressing();

    }

    public interface MainLogic extends MVP.BasePresenter<MainView> {
        void loadData(String url_appender, DisposableSingleObserver disposableSingleObserver);
    }
}
