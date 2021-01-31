package com.example.fashi_shop.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.fashi_shop.Model.Product;
import com.example.fashi_shop.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolderGioHang> {

    Context context;
    List<Product> products;
    int layout;

    public AdapterGioHang(Context context, List<Product> products, int layout){
        this.context = context;
        this.products = products;
        this.layout = layout;
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {
        ImageView imageProduct, imageDelete;
        TextView name, price;

        public ViewHolderGioHang(View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.item_image_cart);
            imageDelete =  itemView.findViewById(R.id.imDeleteItem);
            name = itemView.findViewById(R.id.item_product_name_cart);
            price = itemView.findViewById(R.id.item_product_price_cart);
        }
    }


    @Override
    public AdapterGioHang.ViewHolderGioHang onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        ViewHolderGioHang viewHolderGioHang = new ViewHolderGioHang(view);
        return viewHolderGioHang;
    }

    @Override
    public void onBindViewHolder(AdapterGioHang.ViewHolderGioHang holder, int position) {
        Product product = products.get(position);

        holder.name.setText(product.getName());

        NumberFormat formatter = new DecimalFormat("#,###");
        holder.price.setText(formatter.format(product.getPrice()) + "₫");

        Bitmap bitmapImageCart = BitmapFactory.decodeByteArray(product.getImage_gio_hang(), 0, product.getImage_gio_hang().length);
        holder.imageProduct.setImageBitmap(bitmapImageCart);

        holder.imageDelete.setTag(product.getId());
        holder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


}
