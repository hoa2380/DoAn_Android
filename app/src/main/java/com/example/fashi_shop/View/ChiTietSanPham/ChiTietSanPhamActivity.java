package com.example.fashi_shop.View.ChiTietSanPham;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fashi_shop.Model.Brand;
import com.example.fashi_shop.Model.Category;
import com.example.fashi_shop.Model.Product;
import com.example.fashi_shop.R;
import com.example.fashi_shop.responses.BrandResponse;
import com.example.fashi_shop.responses.CategoryResponse;
import com.example.fashi_shop.responses.ProductResponse;
import com.example.fashi_shop.service.BrandService;
import com.example.fashi_shop.service.CategoryService;
import com.example.fashi_shop.service.ProductService;
import com.example.fashi_shop.utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    private ImageView image;
    private TextView name;
    private TextView price;
    private TextView tvBrand;
    private TextView tvcategory;
    private RatingBar vote;
    private TextView desc;
    ImageButton ibXemThem;
    boolean check = false;

    ProductService productService;
    BrandService brandService;
    CategoryService categoryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);
        image = findViewById(R.id.ivItemDetailImage);
        name = findViewById(R.id.product_detail_name);
        price = findViewById(R.id.product_detail_price);
        tvBrand = findViewById(R.id.product_detail_brand);
        tvcategory = findViewById(R.id.product_detail_category);
        vote = findViewById(R.id.product_detail_vote);
        desc = findViewById(R.id.product_detail_desc);
        ibXemThem = findViewById(R.id.ibXemThem);

        productService = ApiClient.getProductService();
        brandService = ApiClient.getBrandService();
        categoryService = ApiClient.getCategoryService();


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

    void loadCategory(int id){
        categoryService.getCategories(id).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()){
                    Category category = response.body().category;
                    System.out.println(category);
                    tvcategory.setText(category.name);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
    }

    void loadBrand(int id){
        brandService.getBrands(id).enqueue(new Callback<BrandResponse>() {
            @Override
            public void onResponse(Call<BrandResponse> call, Response<BrandResponse> response) {
                if (response.isSuccessful()){
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

    void loadProduct(long id) {
        productService.getProduct(id).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    Product product = response.body().product;
                    System.out.println(product);
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
                                if (check ) {
                                    desc.setText(product.desc);
                                }else {
                                    desc.setText(product.desc.substring(0, 100));
                                }
                            }
                        });
                    }

                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }

}