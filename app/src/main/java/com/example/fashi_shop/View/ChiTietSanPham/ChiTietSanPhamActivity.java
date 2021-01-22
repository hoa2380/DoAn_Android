package com.example.fashi_shop.View.ChiTietSanPham;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fashi_shop.Model.Product;
import com.example.fashi_shop.R;
import com.example.fashi_shop.responses.ProductResponse;
import com.example.fashi_shop.service.ProductService;
import com.example.fashi_shop.utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    private ImageView image;
    private TextView name;
    private TextView price;
    private TextView brand;
    private TextView category;
    private TextView desc;

    ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);
        image = findViewById(R.id.ivItemDetailImage);
        name = findViewById(R.id.product_detail_name);
        price = findViewById(R.id.product_detail_price);
        brand = findViewById(R.id.product_detail_brand);
        category = findViewById(R.id.product_detail_category);
        desc = findViewById(R.id.product_detail_desc);

        productService = ApiClient.getProductService();
        loadProduct(getIntent().getLongExtra("productID", 0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuchitietsanpham,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    void loadProduct(long id) {
        productService.getProduct(id).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    Product product = response.body().product;
                    System.out.println(product);
                    Glide.with(getApplicationContext()).load(product.image).into(image);
                    name.setText(product.name);
                    price.setText(product.price + "₫");
                    desc.setText(product.desc);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }
}
