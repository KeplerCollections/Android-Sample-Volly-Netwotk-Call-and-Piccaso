package com.kepler.codechallenge;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.toolbox.NetworkImageView;
import com.kepler.codechallenge.boilers.BaseFragment;
import com.kepler.codechallenge.boilers.BaseFragmentCommunicator;
import com.kepler.codechallenge.pojo.LocationDetails;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;

import static com.kepler.codechallenge.support.Constants.PARAM_DATA;
import static com.kepler.codechallenge.support.Constants.PARAM_IMG_URL;

public class FragmentDeliveryDetail extends BaseFragment<BaseFragmentCommunicator> {

    @BindView(R.id.des)
    TextView des;
   @BindView(R.id.imageview)
   NetworkImageView networkImageView;
    private LocationDetails locationDetails;
    private String imgUrl;

    public static FragmentDeliveryDetail getInstance(@NotNull Bundle bundle) {
        FragmentDeliveryDetail fragmentDeliveryDetail = new FragmentDeliveryDetail();
        fragmentDeliveryDetail.setArguments(bundle);
        return fragmentDeliveryDetail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communicator.handleBackButton(true);
        locationDetails = getArguments().getParcelable(PARAM_DATA);
        imgUrl = getArguments().getString(PARAM_IMG_URL);
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
        des.setText(locationDetails.getAddress());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
