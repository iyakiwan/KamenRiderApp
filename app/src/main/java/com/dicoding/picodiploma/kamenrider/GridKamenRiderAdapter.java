package com.dicoding.picodiploma.kamenrider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridKamenRiderAdapter extends RecyclerView.Adapter<GridKamenRiderAdapter.GridViewHolder> {

    private Context context;
    private ArrayList<KamenRider> listKamenRider;

    public GridKamenRiderAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<KamenRider> getListKamenRider() {
        return listKamenRider;
    }

    public void setListKamenRider(ArrayList<KamenRider> listKamenRider) {
        this.listKamenRider = listKamenRider;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_kr, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder gridViewHolder, int i) {
        Glide.with(context).load(getListKamenRider().get(i).getPhoto())
                .apply(new RequestOptions().override(746, 981))
                .into(gridViewHolder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return getListKamenRider().size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
