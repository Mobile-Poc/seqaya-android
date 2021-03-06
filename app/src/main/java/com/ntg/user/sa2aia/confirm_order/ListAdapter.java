package com.ntg.user.sa2aia.confirm_order;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.ViewUtil;
import com.ntg.user.sa2aia.model.CartItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    List<CartItem> orderList;
    Context context;


    public ListAdapter(List<CartItem> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_row, parent, false);
        ViewUtil.addShadowToView(context, row);
        return new ListViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

        CartItem cartItem = orderList.get(position);
        Glide.with(context)
                .load(context.getResources()
                        .getIdentifier(cartItem.getProduct().getPhotoUrl(), "drawable", context.getPackageName()))
                .into(holder.productImage);
        holder.productName.setText(cartItem.getProduct().getName());
        holder.price.setText(String.valueOf(cartItem.getProduct().getPrice()));
        holder.bottleSize.setText(String.valueOf(cartItem.getProduct().getBottleSize()));
        holder.noInPackage.setText(String.valueOf(cartItem.getProduct().getNo_bpp()));
        holder.productManufacturer.setText(cartItem.getProduct().getManufacturer());
        holder.numberOfItem.setText(String.valueOf(cartItem.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_name)
        TextView productName;
        @BindView(R.id.product_manufacturer)
        TextView productManufacturer;
        @BindView(R.id.bottle_size)
        TextView bottleSize;
        @BindView(R.id.no_in_package)
        TextView noInPackage;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.number_of_item)
        TextView numberOfItem;
        @BindView(R.id.product_image)
        ImageView productImage;

        ListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}



