package com.kepler.codechallenge.boilers;

public interface BaseFragmentCommunicator {
    void setFragmentTitle(int title);

    void toast(String msg);

    void replaceNewFragment(BaseFragment baseFragment);

    void handleBackButton(boolean enable);
}