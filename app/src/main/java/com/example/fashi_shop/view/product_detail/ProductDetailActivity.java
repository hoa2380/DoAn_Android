package com.example.fashi_shop.view.product_detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.fashi_shop.model.Brand;
import com.example.fashi_shop.model.Category;
import com.example.fashi_shop.model.Product;
import com.example.fashi_shop.presenter.product_detail.PresenterProductDetail;
import com.example.fashi_shop.R;
import com.example.fashi_shop.view.cart.CartActivity;
import com.example.fashi_shop.view.checkout.CheckoutActivity;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.ByteArrayOutputStream;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener, ViewProductDetail {
    ImageView image;
    TextView name, price, tvBrand, tvCategory, desc, txtSoLuongSanPhamGioHang;
    RatingBar vote;
    ImageButton ibXemThem, ibAddToCart;
    Button btnBuyOne;
    boolean check = false;
    MaterialToolbar toolbar;

    Product productCart;
    PresenterProductDetail presenterLogicChiTietSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);
        toolbar = findViewById(R.id.toolBarChiTietSanPham);
        image = findViewById(R.id.ivItemDetailImage);
        name = findViewById(R.id.product_detail_name);
        price = findViewById(R.id.product_detail_price);
        tvBrand = findViewById(R.id.product_detail_brand);
        tvCategory = findViewById(R.id.product_detail_category);
        vote = findViewById(R.id.product_detail_vote);
        desc = findViewById(R.id.product_detail_desc);
        ibXemThem = findViewById(R.id.ibXemThem);
        ibAddToCart = findViewById(R.id.ibAddToCart);
        btnBuyOne = findViewById(R.id.btnBuyOne);
        setSupportActionBar(toolbar);

        presenterLogicChiTietSanPham = new PresenterProductDetail(this);
        presenterLogicChiTietSanPham.getProduct(getIntent().getLongExtra("productID", 0));

        ibAddToCart.setOnClickListener(this);
        btnBuyOne.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        MenuItem itemGioHang = menu.findItem(R.id.itemGioHang);
        View viewCustomGioHang = itemGioHang.getActionView();
        viewCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        txtSoLuongSanPhamGioHang = viewCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);
        txtSoLuongSanPhamGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.countProductCart(this)));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private Drawable getIconXemThem(int idDrawable) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT > 21) {
            drawable = ContextCompat.getDrawable(this, idDrawable);
        } else {
            drawable = getResources().getDrawable(idDrawable);
        }
        return drawable;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnBuyOne:
                Intent intent = new Intent(ProductDetailActivity.this, CheckoutActivity.class);
                startActivity(intent);
                break;
            case R.id.ibAddToCart:
                ImageView imageView = findViewById(R.id.ivItemDetailImage);
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                System.out.println("productcart: " + productCart);
//                ibAddToCart.setImageBitmap(bitmap);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] imageProductCart = byteArrayOutputStream.toByteArray();
                productCart.setImage_gio_hang(imageProductCart);
               presenterLogicChiTietSanPham.addToCart(productCart, this);
                break;
        }
    }

    @Override
    public void showProduct(Product product) {
        productCart = product;
        presenterLogicChiTietSanPham.getBrand(product.product_brands_id);
        presenterLogicChiTietSanPham.getCategory(product.categories_id);

        Glide.with(getApplicationContext()).load(product.image).into(image);
        name.setText(product.name);
        price.setText(product.price + " ₫");
        desc.setText(product.desc.substring(0, 100));
        if (product.desc.length() < 30) {
            ibXemThem.setVisibility(View.GONE);
        } else {
            ibXemThem.setVisibility(View.VISIBLE);
            ibXemThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    check = !check;
                    if (check) {
                        desc.setText(product.desc);
                        ibXemThem.setImageDrawable(getIconXemThem(R.drawable.ic_keyboard_arrow_up_black_24dp));
                    } else {
                        desc.setText(product.desc.substring(0, 100));
                        ibXemThem.setImageDrawable(getIconXemThem(R.drawable.ic_keyboard_arrow_down_black_24dp));
                    }
                }
            });
        }
    }

    @Override
    public void showBrand(Brand brand) {
        tvBrand.setText(brand.name);
    }

    @Override
    public void showCategory(Category category) {
        tvCategory.setText(category.name);
    }

    @Override
    public void addToCartSuccess() {
        Toast.makeText(this, "Đã thêm sản phẩm vào giỏ hàng",Toast.LENGTH_SHORT).show();
        txtSoLuongSanPhamGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.countProductCart(this)));
    }

    @Override
    public void addToCartFail() {
        Toast.makeText(this, "Sản phẩm đã có trong giỏ hàng",Toast.LENGTH_SHORT).show();
    }
}