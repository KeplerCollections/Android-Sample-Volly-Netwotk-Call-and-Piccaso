package com.kepler.codechallenge;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.kepler.codechallenge.boilers.BaseFragment;
import com.kepler.codechallenge.boilers.BaseFragmentCommunicator;
import com.kepler.codechallenge.boilers.MainFragmentCommunicator;

import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class FragmentDeliveryDetail extends BaseFragment<BaseFragmentCommunicator> {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        communicator.setFragmentTitle(R.string.delivery_details);
    }
    @Override
    protected int getContentView() {
        return R.layout.fragment_lod;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
