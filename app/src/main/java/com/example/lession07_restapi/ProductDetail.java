package com.example.lession07_restapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.lession07_restapi.models.Product;
import com.example.lession07_restapi.responses.ProductResponse;
import com.example.lession07_restapi.service.ProductService;
import com.example.lession07_restapi.utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetail extends AppCompatActivity {
    private ImageView image;
    private TextView name;
    private TextView price;
    private TextView brand;
    private TextView category;
    private RatingBar vote;
    private TextView desc;

    ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        image = findViewById(R.id.ivItemDetailImage);
        name = findViewById(R.id.product_detail_name);
        price = findViewById(R.id.product_detail_price);
        brand = findViewById(R.id.product_detail_brand);
        category = findViewById(R.id.product_detail_category);
        vote = findViewById(R.id.product_detail_vote);
        desc = findViewById(R.id.product_detail_desc);

        productService = ApiClient.getProductService();
        loadProduct(getIntent().getLongExtra("productID", 0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_detail,menu);
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