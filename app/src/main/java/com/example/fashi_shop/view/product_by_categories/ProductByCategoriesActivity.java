package com.example.fashi_shop.view.product_by_categories;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashi_shop.adapter.ProductAdapter;
import com.example.fashi_shop.model.Product;
import com.example.fashi_shop.presenter.product_by_categories.PresenterProductByCategories;
import com.example.fashi_shop.R;
import com.example.fashi_shop.view.product_detail.ProductDetailActivity;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class ProductByCategoriesActivity extends AppCompatActivity implements ViewProductByCategories, View.OnClickListener {
    RecyclerView recyclerViewSanPhamTheoDanhMuc;
    Button btnChangeViewList;
    boolean gridViewProduct = true;
    RecyclerView.LayoutManager layoutManager;
    PresenterProductByCategories sanPhamTheoDanhMuc;
    ProductAdapter productAdapter;
    int categoriesId;
    String nameCategory;
    MaterialToolbar toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sanphamtheodanhmuc);

        toolbar = findViewById(R.id.toolBar);
        nameCategory = getIntent().getStringExtra("categoryName");
        toolbar.setTitle(nameCategory);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        recyclerViewSanPhamTheoDanhMuc = findViewById(R.id.recyclerViewSanPhamTheoDanhMuc);
        btnChangeViewList = findViewById(R.id.btnChangeViewList);

        categoriesId = getIntent().getIntExtra("categoriesID", 0);

        sanPhamTheoDanhMuc = new PresenterProductByCategories(this);
        sanPhamTheoDanhMuc.getProducts(categoriesId);
        btnChangeViewList.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public void loadProducts(List<Product> products) {
        if (gridViewProduct) {
            layoutManager = new GridLayoutManager(ProductByCategoriesActivity.this, 2);
            productAdapter = new ProductAdapter(this, products, R.layout.item_product, id -> {
                Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                intent.putExtra("productID",id);
                startActivity(intent);
            });
        } else {
            layoutManager = new LinearLayoutManager(ProductByCategoriesActivity.this);
            productAdapter = new ProductAdapter(this, products, R.layout.custom_layout_list_product, id -> {
                Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                intent.putExtra("productID",id);
                startActivity(intent);
            });
        }
        recyclerViewSanPhamTheoDanhMuc.setLayoutManager(layoutManager);
        recyclerViewSanPhamTheoDanhMuc.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void errorLoadProducts() {}

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnChangeViewList:
            gridViewProduct = !gridViewProduct;
            sanPhamTheoDanhMuc.getProducts(categoriesId);
            ;break;
        }
    }

}
