package com.dicoding.picodiploma.kamenrider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.lang.annotation.Target;
import java.util.ArrayList;

public class ListKamenRiderAdapter extends RecyclerView.Adapter<ListKamenRiderAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<KamenRider> listKamenRider;

    public ListKamenRiderAdapter(Context context) {
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
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_kr, viewGroup, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.tvName.setText(getListKamenRider().get(i).getName());
        categoryViewHolder.tvHeisei.setText(getListKamenRider().get(i).getHeisei());
        Glide.with(context).load(getListKamenRider().get(i).getLogo())
                .apply(new RequestOptions())
                .into(categoryViewHolder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return getListKamenRider().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvHeisei;
        ImageView imgPhoto;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_kr_name);
            tvHeisei = itemView.findViewById(R.id.tv_kr_heisei);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
