package com.kepler.codechallenge;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kepler.codechallenge.boilers.BaseFragment;
import com.kepler.codechallenge.boilers.BaseFragmentCommunicator;
import com.kepler.codechallenge.pojo.DeliveriesDetails;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.maps.SupportMapFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

import static com.kepler.codechallenge.support.Constants.PARAM_DATA;


public class FragmentDeliveryDetail extends BaseFragment<BaseFragmentCommunicator> implements OnMapReadyCallback {

    private static final String AT = " at ";
    @BindView(R.id.des)
    TextView des;
    @BindView(R.id.imageview)
    ImageView imageView;
    private DeliveriesDetails deliveriesDetails;
    private SupportMapFragment mapFragment;

    public static FragmentDeliveryDetail getInstance(Bundle bundle) {
        FragmentDeliveryDetail fragmentDeliveryDetail = new FragmentDeliveryDetail();
        fragmentDeliveryDetail.setArguments(bundle);
        return fragmentDeliveryDetail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communicator.handleBackButton(true);
        deliveriesDetails = getArguments().getParcelable(PARAM_DATA);
        MapboxMapOptions options = MapboxMapOptions.createFromAttributes(getContext(), null);
        options.camera(new CameraPosition.Builder()
                .target(new LatLng(deliveriesDetails.getLocation().getLat(), deliveriesDetails.getLocation().getLng()))
                .zoom(12)
                .build());
        mapFragment = SupportMapFragment.newInstance(options);
    }


    @Override
    protected int getFragmentTitle() {
        return R.string.delivery_details;
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_dd;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        des.setText(deliveriesDetails.getDescription() + AT + deliveriesDetails.getLocation().getAddress());
        Picasso.get()
                .load(deliveriesDetails.getImageUrl())
                .placeholder(android.R.drawable.stat_sys_download_done)
                .resize(50, 50)
                .centerCrop()
                .into(imageView);
        addMapFragment(mapFragment);
        mapFragment.getMapAsync(this);
    }

    private void addMapFragment(SupportMapFragment newInstance) {
        getChildFragmentManager().beginTransaction().add(R.id.child_container, newInstance).commit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(deliveriesDetails.getLocation().getLat(), deliveriesDetails.getLocation().getLng()))
                        .title(des.getText().toString()));
//                mapboxMap.setCameraPosition(getCameraPosition());

            }

            private CameraPosition getCameraPosition() {
                LatLng target = new LatLng(
                        deliveriesDetails.getLocation().getLat(),
                        deliveriesDetails.getLocation().getLng()
                );
                return new CameraPosition.Builder()
                        .target(target)
                        .build();
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
