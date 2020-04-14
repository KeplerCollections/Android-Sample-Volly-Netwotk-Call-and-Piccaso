package com.kepler.codechallenge.presenters;

import android.content.Context;

import com.kepler.codechallenge.boilers.MVP;

public class AppLogic {

    /********* Logic for Main************/
    public interface MainView extends MVP.BaseView {
    }

    public interface MainLogic extends MVP.BasePresenter<MainView> {
    }
}
