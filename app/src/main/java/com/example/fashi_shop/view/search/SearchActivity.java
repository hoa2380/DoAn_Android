package com.example.fashi_shop.view.search;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.fashi_shop.R;
import com.example.fashi_shop.adapter.ProductAdapter;
import com.example.fashi_shop.model.Product;
import com.example.fashi_shop.presenter.search.PresenterSearch;
import com.example.fashi_shop.view.product_by_categories.ProductByCategoriesActivity;
import com.example.fashi_shop.view.product_detail.ProductDetailActivity;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements ViewSearch, SearchView.OnQueryTextListener {
    MaterialToolbar toolbar;
    RecyclerView recyclerSearch;
    RecyclerView.LayoutManager layoutManager;
    PresenterSearch presenterSearch;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);

        toolbar = findViewById(R.id.toolBarSearch);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Tìm kiếm");
        setSupportActionBar(toolbar);

        presenterSearch = new PresenterSearch(this);
        recyclerSearch = findViewById(R.id.recyclerSearch);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem itSearch = menu.findItem(R.id.itSearch);
        SearchView searchView = (SearchView) itSearch.getActionView();
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public void searchSuccess(List<Product> products) {
        layoutManager = new LinearLayoutManager(this);
        productAdapter = new ProductAdapter(this, products, R.layout.custom_layout_list_product, id -> {
            Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
            intent.putExtra("productID",id);
            startActivity(intent);
        });
        recyclerSearch.setLayoutManager(layoutManager);
        recyclerSearch.setAdapter(productAdapter);
    }

    @Override
    public void searchFailure() {
        Toast.makeText(this, "Không tìm thấy sản phẩm",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenterSearch.getSearchProducts(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
