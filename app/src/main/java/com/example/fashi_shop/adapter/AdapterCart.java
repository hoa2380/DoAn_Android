package com.example.fashi_shop.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fashi_shop.model.cart.Cart;
import com.example.fashi_shop.model.Product;
import com.example.fashi_shop.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolderGioHang> {

    Context context;
    List<Product> products;
    int layout;

    public AdapterCart(Context context, List<Product> products, int layout){
        this.context = context;
        this.products = products;
        this.layout = layout;
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {
        ImageView imageProduct, imageDelete;
        ImageButton quantityUp, quantityDown;
        TextView name, price, quantity;

        public ViewHolderGioHang(View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.item_image_cart);
            imageDelete =  itemView.findViewById(R.id.imDeleteItem);
            quantityUp = itemView.findViewById(R.id.ibQuantityUp);
            quantityDown = itemView.findViewById(R.id.ibQuantityDown);
            name = itemView.findViewById(R.id.item_product_name_cart);
            price = itemView.findViewById(R.id.item_product_price_cart);
            quantity = itemView.findViewById(R.id.txtQuantity);
        }
    }


    @Override
    public AdapterCart.ViewHolderGioHang onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        ViewHolderGioHang viewHolderCart = new ViewHolderGioHang(view);
        return viewHolderCart;
    }

    @Override
    public void onBindViewHolder(AdapterCart.ViewHolderGioHang holder, int position) {
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
                Cart cart = new Cart();
                cart.ConnectSQL(context);
                cart.DeleteProduct((int)v.getTag());
                products.remove(position);
                Toast.makeText(context, "Đã xóa sản phẩm khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


}
