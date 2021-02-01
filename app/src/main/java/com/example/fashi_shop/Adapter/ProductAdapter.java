package com.example.fashi_shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fashi_shop.Model.Product;
import com.example.fashi_shop.R;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private static final String TAG = "ProductAdapter";
    Context context;
    List<Product> products;
    int layout;
    ProductItemListener productItemListener;

    public ProductAdapter(Context context, List<Product> products, int layout, ProductItemListener productItemListener) {
        this.context = context;
        this.products = products;
        this.productItemListener = productItemListener;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view,productItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NumberFormat formatter = new DecimalFormat("#,###");

        Glide.with(context).load(products.get(position).image).placeholder(R.drawable.placeholder).into(holder.image);
        holder.name.setText(products.get(position).name + "");
        holder.price.setText(formatter.format(products.get(position).price) + "₫");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView name;
        TextView price;
        ProductItemListener productItemListener;

        public ViewHolder(@NonNull View itemView, ProductItemListener productItemListener) {
            super(itemView);
            image = itemView.findViewById(R.id.item_items_image);
            name = itemView.findViewById(R.id.item_product_name);
            price = itemView.findViewById(R.id.item_product_price);
            this.productItemListener = productItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.productItemListener.onItemClick(getProduct(getAdapterPosition()).id);
            notifyDataSetChanged();
        }
    }

    private Product getProduct(int i) {
        return products.get(i);
    }

    public interface ProductItemListener {
        void onItemClick(long id);
    }
}
