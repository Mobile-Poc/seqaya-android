package com.ntg.user.sa2aia.products;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.ntg.user.sa2aia.Checkout.CartItemsCountListener;
import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.ViewUtil;
import com.ntg.user.sa2aia.model.CartItem;
import com.ntg.user.sa2aia.model.Product;
import com.ntg.user.sa2aia.model.ShoppingCart;
import com.ntg.user.sa2aia.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    private List<Product> productFavouriteList;
    private Context context;
    private CartItemsCountListener countListener;
    private FavouriteButtonClickListener favouriteButtonClickListener;

    public ProductAdapter(List<Product> productList, List<Product> productFavouriteList,
                          Context context,
                          CartItemsCountListener countListener, FavouriteButtonClickListener favouriteButtonClickListener) {
        this.productList = productList;
        this.productFavouriteList = productFavouriteList;
        this.context = context;
        this.countListener = countListener;
        this.favouriteButtonClickListener = favouriteButtonClickListener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_row, parent, false);
        view.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        ViewUtil.addShadowToView(context, view);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Product product = productList.get(position);

        Glide.with(context)
                .load(context.getResources()
                        .getIdentifier(product.getPhotoUrl(), "drawable", context.getPackageName()))
                .into(holder.productImage);
        holder.name.setText(product.getName());
        holder.manufacturer.setText(product.getManufacturer());
        holder.bottleSize.setText(String.valueOf(product.getBottleSize()) + "لتر");
        holder.numberInPackage.setText(String.valueOf(product.getNo_bpp()) + "زجاجة");
        holder.price.setText(String.valueOf(product.getPrice()));
        holder.increase.setOnClickListener(view -> {
            int number = Integer.parseInt(holder.numberOfItem.getText().toString());
            number++;
            holder.numberOfItem.setText(String.valueOf(number));
        });
        holder.decrease.setOnClickListener(view -> {
            int number = Integer.parseInt(holder.numberOfItem.getText().toString());
            if (number > 1) {
                number--;
                holder.numberOfItem.setText(String.valueOf(number));
            }

        });
        holder.addToCart.setOnClickListener(view -> {
            ShoppingCart shoppingCart = User.getShoppingCart();
            CartItem cartItem = new CartItem(product, Integer.parseInt(holder.numberOfItem.getText().toString()));
            for (CartItem item : shoppingCart.getCartItemList()) {
                if (item.getProduct().getId() == product.getId()) {
                    shoppingCart.getCartItemList().remove(item);
                }
            }
            shoppingCart.getCartItemList().add(cartItem);
            countListener.onCartItemsCountChanged(shoppingCart.getCartItemList().size());
        });

        if (productFavouriteList != null) {
            for (int i = 0; i < productFavouriteList.size(); i++) {
                Log.e("fav", productFavouriteList.get(i).getName());
                if (product.getId() == productFavouriteList.get(i).getId()) {
                    holder.likeButton.setLiked(true);
                }

            }
        }


        holder.likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                favouriteButtonClickListener.onLike(product);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                favouriteButtonClickListener.onUnLike(product);
            }
        });

        if (holder.likeButton.isLiked()) {
            holder.likeButton.setLiked(false);
        } else {
            holder.likeButton.setLiked(true);
        }
    }

    public void setProductList(List<Product> productList) {
        this.productList.clear();
        this.productList = productList;
    }

    void setProductFavouriteList(List<Product> favouriteList) {
        this.productFavouriteList = favouriteList;
        notifyDataSetChanged();
    }

    public void clear() {
        this.productList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_image)
        ImageView productImage;
        @BindView(R.id.product_name)
        TextView name;
        @BindView(R.id.product_manufacturer)
        TextView manufacturer;
        @BindView(R.id.bottle_size)
        TextView bottleSize;
        @BindView(R.id.number_in_package)
        TextView numberInPackage;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.decrease)
        ImageButton decrease;
        @BindView(R.id.number_of_item)
        TextView numberOfItem;
        @BindView(R.id.increase)
        ImageButton increase;
        @BindView(R.id.add_to_cart)
        Button addToCart;
        LikeButton likeButton;


        ProductViewHolder(View itemView) {
            super(itemView);
            likeButton = itemView.findViewById(R.id.fav);
            ButterKnife.bind(this, itemView);
        }
    }
}
