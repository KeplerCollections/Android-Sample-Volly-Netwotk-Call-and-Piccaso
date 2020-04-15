package com.kepler.codechallenge.boilers;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kepler.codechallenge.support.interfaces.MainFragmentCommunicator;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T extends BaseFragmentCommunicator> extends Fragment {

    protected T communicator;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainFragmentCommunicator)
            communicator = (T) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        communicator.setFragmentTitle(getFragmentTitle());
    }

    protected abstract int getFragmentTitle();

    @Override
    public void onPause() {
        super.onPause();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(getViewResource(), container, false);
        View view = inflater.inflate(getContentView(), container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    protected abstract int getContentView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}