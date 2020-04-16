package com.kepler.codechallenge.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class DeliveriesDetails implements Parcelable {
    private int id;
    private String description;
    private String imageUrl;
    private LocationDetails location;

    protected DeliveriesDetails(Parcel in) {
        description = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<DeliveriesDetails> CREATOR = new Creator<DeliveriesDetails>() {
        @Override
        public DeliveriesDetails createFromParcel(Parcel in) {
            return new DeliveriesDetails(in);
        }

        @Override
        public DeliveriesDetails[] newArray(int size) {
            return new DeliveriesDetails[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LocationDetails getLocation() {
        return location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(imageUrl);
    }
}
