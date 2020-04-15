package com.kepler.codechallenge;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.kepler.codechallenge.boilers.BaseFragment;
import com.kepler.codechallenge.boilers.MainFragmentCommunicator;

import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class FragmentListOfDeliveries extends BaseFragment<MainFragmentCommunicator> {

    private DisposableSingleObserver<String> observer;
    private Gson gson=new Gson();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observer = getObserver();
    }

    @Override
    public void onResume() {
        super.onResume();
        communicator.setFragmentTitle(R.string.deliveries);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_lod;
    }


    private DisposableSingleObserver<String> getObserver() {
        return new DisposableSingleObserver<String>() {
            @Override
            public void onSuccess(@NonNull String posts) {
                Log.e("ddd", posts);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                communicator.toast(e.getMessage());
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        communicator.loadDeleiveries(observer);
    }

    @Override
    public void onDestroyView() {
        observer.dispose();
        super.onDestroyView();

    }
}
