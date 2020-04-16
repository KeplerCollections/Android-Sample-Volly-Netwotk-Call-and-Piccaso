package com.kepler.codechallenge;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.toolbox.ImageLoader;
import com.kepler.codechallenge.pojo.DeliveriesDetails;
import com.kepler.codechallenge.support.interfaces.SetOnRecyclerViewItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DeliveryViewAdapter extends RecyclerView.Adapter<DeliveryViewAdapter.LayoutViewHolder> {

    private final SetOnRecyclerViewItemClickListener setOnRecyclerViewItemClickListener;
    private List<DeliveriesDetails> dataSet = new ArrayList<>();

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
        Picasso.get()
                .load(dataSet.get(position).getImageUrl())
                .placeholder(android.R.drawable.stat_sys_download_done)
                .resize(50, 50)
                .centerCrop()
                .into(holder.imageview);
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
        ImageView imageview;

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