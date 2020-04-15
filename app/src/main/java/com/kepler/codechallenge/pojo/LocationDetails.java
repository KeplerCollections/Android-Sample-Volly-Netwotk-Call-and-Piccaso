package com.kepler.codechallenge.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class LocationDetails implements Parcelable {
    private double lat;
    private double lng;
    private String address;

    protected LocationDetails(Parcel in) {
        lat = in.readDouble();
        lng = in.readDouble();
        address = in.readString();
    }

    public static final Creator<LocationDetails> CREATOR = new Creator<LocationDetails>() {
        @Override
        public LocationDetails createFromParcel(Parcel in) {
            return new LocationDetails(in);
        }

        @Override
        public LocationDetails[] newArray(int size) {
            return new LocationDetails[size];
        }
    };

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeString(address);
    }
}
