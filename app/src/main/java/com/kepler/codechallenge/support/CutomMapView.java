package com.kepler.codechallenge.support;

import android.content.Context;

import androidx.annotation.NonNull;

import com.mapbox.mapboxsdk.maps.MapView;

public class CutomMapView extends MapView {
    public CutomMapView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
