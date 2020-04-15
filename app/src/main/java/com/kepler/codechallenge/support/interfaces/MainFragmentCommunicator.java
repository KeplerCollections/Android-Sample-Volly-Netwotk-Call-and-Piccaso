package com.kepler.codechallenge.support.interfaces;

import com.kepler.codechallenge.api.VolleyCallback;
import com.kepler.codechallenge.api.VolleyController;
import com.kepler.codechallenge.boilers.BaseFragmentCommunicator;

import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public interface MainFragmentCommunicator extends BaseFragmentCommunicator {
    void loadDeleiveries(DisposableSingleObserver disposableSingleObserver) ;
}