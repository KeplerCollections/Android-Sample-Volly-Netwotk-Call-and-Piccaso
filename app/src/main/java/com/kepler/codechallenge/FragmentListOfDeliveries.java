package com.kepler.codechallenge;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kepler.codechallenge.boilers.BaseFragment;
import com.kepler.codechallenge.pojo.DeliveriesDetails;
import com.kepler.codechallenge.pojo.LocationDetails;
import com.kepler.codechallenge.support.interfaces.MainFragmentCommunicator;
import com.kepler.codechallenge.support.interfaces.SetOnRecyclerViewItemClickListener;

import java.util.List;

import butterknife.BindView;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

import static com.kepler.codechallenge.support.Constants.PARAM_DATA;
import static com.kepler.codechallenge.support.Constants.PARAM_IMG_URL;

public class FragmentListOfDeliveries extends BaseFragment<MainFragmentCommunicator> {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private Gson gson=new Gson();
    private DeliveryViewAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new DeliveryViewAdapter(new SetOnRecyclerViewItemClickListener<DeliveriesDetails>() {
            private Bundle bundle;

            @Override
            public void onItemClick(DeliveriesDetails deliveriesDetails) {

                bundle=new Bundle();
                bundle.putParcelable(PARAM_DATA,deliveriesDetails.getLocation());
                bundle.putString(PARAM_IMG_URL,deliveriesDetails.getImageUrl());
                communicator.replaceNewFragment(FragmentDeliveryDetail.getInstance(bundle));
            }
        });
    }

    @Override
    protected int getFragmentTitle() {
        return R.string.deliveries;
    }

    @Override
    public void onResume() {
        communicator.handleBackButton(false);
        super.onResume();
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_lod;
    }


    private DisposableSingleObserver<String> getObserver() {
        return new DisposableSingleObserver<String>() {
            @Override
            public void onSuccess(@NonNull String deliveries) {
                Log.e("ddd", deliveries);
                mAdapter.add(gson.fromJson(deliveries,new TypeToken<List<DeliveriesDetails>>(){}.getType()));

            }

            @Override
            public void onError(@NonNull Throwable e) {
                communicator.toast(e.getMessage());
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        communicator.loadDeleiveries(getObserver());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
