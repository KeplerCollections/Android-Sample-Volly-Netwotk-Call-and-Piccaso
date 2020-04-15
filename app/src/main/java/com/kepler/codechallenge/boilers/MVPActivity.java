package com.kepler.codechallenge.boilers;

import android.os.Bundle;

public abstract class MVPActivity<T extends MVP.BasePresenter> extends BaseActivity implements MVP.BaseView {

    /**
     * The Presenter attached to this View
     */
    protected T presenter;

    /**
     * Must be overriden to define the Presenter used by this activity.
     *
     * @return The presenter that will be used by this View.
     */
    protected abstract T createPresenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.presenter = this.createPresenter();
        if (this.presenter != null) {
            this.presenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (this.presenter != null) {
            this.presenter.detachView();
        }
        super.onDestroy();
    }
}