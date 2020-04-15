package com.kepler.codechallenge.boilers;

import com.kepler.codechallenge.FragmentDeliveryDetail;

public interface BaseFragmentCommunicator {
    void setFragmentTitle(int title);
    void toast(String msg);
    void addNewFragment(BaseFragment baseFragment);
   void replaceNewFragment(BaseFragment baseFragment);
    void handleBackButton(boolean enable);
}