package com.kepler.codechallenge.presenters;

import androidx.annotation.NonNull;

import com.google.gson.JsonArray;
import com.kepler.codechallenge.api.VolleyCallback;
import com.kepler.codechallenge.api.VolleyController;
import com.kepler.codechallenge.boilers.MVPImpl;
import com.kepler.codechallenge.pojo.DeliveriesDetails;

import org.json.JSONArray;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainPresnter extends MVPImpl<AppLogic.MainView> implements AppLogic.MainLogic {

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public void loadData(String url_appender, DisposableSingleObserver disposableSingleObserver) {
        if (view == null)
            return;

        Disposable subscription = getDeliveries(url_appender)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(disposableSingleObserver);

        // add the subscription to the list to avoid a possible leak of references
        disposable.add(subscription);

    }


    public Single<String> getDeliveries(String url_appender) {
        return Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull final SingleEmitter<String> e) {
                VolleyController.getInstance(view.getApplicationContext()).jsonArrayRequest(url_appender, new VolleyCallback<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        e.onSuccess(response.toString());
                    }

                    @Override
                    public void onError(Throwable errorMessage) {
                        try {
                            if(!e.isDisposed())
                            e.onError(errorMessage);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                });
            }
        });
    }
}
