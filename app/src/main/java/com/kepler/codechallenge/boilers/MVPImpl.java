package com.kepler.codechallenge.boilers;

public abstract class MVPImpl<T extends MVP.BaseView> implements MVP.BasePresenter<T> {

    /**
     * The View linked to this Presenter
     */
    protected T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }


    @Override
    public void detachView() {
        this.view = null;
    }
}