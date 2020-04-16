package com.kepler.codechallenge.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class LocationDetails {
    private double lat;
    private double lng;
    private String address;


    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getAddress() {
        return address;
    }

}
