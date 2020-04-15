package com.kepler.codechallenge;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.toolbox.NetworkImageView;
import com.kepler.codechallenge.pojo.DeliveriesDetails;
import com.kepler.codechallenge.support.interfaces.SetOnRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DeliveryViewAdapter extends RecyclerView.Adapter<DeliveryViewAdapter.LayoutViewHolder> {

    private final SetOnRecyclerViewItemClickListener setOnRecyclerViewItemClickListener;
    private List<DeliveriesDetails> dataSet = new ArrayList<>();
    private int lastSelectedPosition = -1;

    public DeliveryViewAdapter(SetOnRecyclerViewItemClickListener setOnRecyclerViewItemClickListener) {
        this.setOnRecyclerViewItemClickListener = setOnRecyclerViewItemClickListener;
    }

    @Override
    public LayoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delivery_view, parent, false);
        return new LayoutViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LayoutViewHolder holder, int position) {
        holder.des.setText(dataSet.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void add(List<DeliveriesDetails> deliveriesDetails) {
        this.dataSet.addAll(deliveriesDetails);
        notifyDataSetChanged();
    }

    public class LayoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.des)
        TextView des;
        @BindView(R.id.imageview)
        NetworkImageView imageview;

        public LayoutViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            setOnRecyclerViewItemClickListener.onItemClick(dataSet.get(getAdapterPosition()));
        }
    }

}