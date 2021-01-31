package com.example.fashi_shop.View.ChiTietSanPham;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuCompat;
import androidx.core.view.MenuItemCompat;

import com.bumptech.glide.Glide;
import com.example.fashi_shop.Model.Brand;
import com.example.fashi_shop.Model.Category;
import com.example.fashi_shop.Model.Product;
import com.example.fashi_shop.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.fashi_shop.R;
import com.example.fashi_shop.responses.BrandResponse;
import com.example.fashi_shop.responses.CategoryResponse;
import com.example.fashi_shop.responses.ProductResponse;
import com.example.fashi_shop.service.BrandService;
import com.example.fashi_shop.service.CategoryService;
import com.example.fashi_shop.service.ProductService;
import com.example.fashi_shop.utils.ApiClient;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSanPhamActivity extends AppCompatActivity implements View.OnClickListener, ViewChiTietSanPham {
    ImageView image;
    TextView name, price, tvBrand, tvCategory, desc, txtSoLuongSanPhamGioHang;
    RatingBar vote;
    ImageButton ibXemThem, ibAddToCart;
    boolean check = false;
    MaterialToolbar toolbar;

    ProductService productService;
    BrandService brandService;
    CategoryService categoryService;
    Product productCart;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;

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
        setSupportActionBar(toolbar);

        productService = ApiClient.getProductService();
        brandService = ApiClient.getBrandService();
        categoryService = ApiClient.getCategoryService();


        loadProduct(getIntent().getLongExtra("productID", 0));

        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        ibAddToCart.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);

        MenuItem itemGioHang = menu.findItem(R.id.itemGioHang);
        View viewCustomGioHang = itemGioHang.getActionView();
        txtSoLuongSanPhamGioHang = viewCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);
        txtSoLuongSanPhamGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.countProductCart(this)));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    void loadCategory(int id) {
        categoryService.getCategory(id).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    Category category = response.body().category;
                    System.out.println(category);
                    tvCategory.setText(category.name);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
    }

    void loadBrand(int id) {
        brandService.getBrands(id).enqueue(new Callback<BrandResponse>() {
            @Override
            public void onResponse(Call<BrandResponse> call, Response<BrandResponse> response) {
                if (response.isSuccessful()) {
                    Brand brand = response.body().brand;
                    System.out.println(brand);
                    tvBrand.setText(brand.name);
                }
            }

            @Override
            public void onFailure(Call<BrandResponse> call, Throwable t) {

            }
        });
    }

    public void loadProduct(long id) {
        productService.getProduct(id).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    Product product = response.body().product;
                    productCart = product;
                    Glide.with(getApplicationContext()).load(product.image).into(image);
                    name.setText(product.name);
                    price.setText(product.price + " ₫");
                    desc.setText(product.desc.substring(0, 100));
                    loadBrand(product.product_brands_id);
                    loadCategory(product.categories_id);
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

                } else {
                    Log.e("Code", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
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
    public void LoadProduct(Product product) {

    }

    @Override
    public void AddToCartSuccess() {
        Toast.makeText(this, "OK",Toast.LENGTH_SHORT).show();
        txtSoLuongSanPhamGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.countProductCart(this)));
    }

    @Override
    public void AddToCartFail() {
        Toast.makeText(this, "Đã có trong giỏ á!",Toast.LENGTH_SHORT).show();
    }
}