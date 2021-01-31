package com.example.fashi_shop.View.SanPhamTheoDanhMuc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashi_shop.Adapter.ProductAdapter;
import com.example.fashi_shop.Model.ILoadMore;
import com.example.fashi_shop.Model.LoadMore;
import com.example.fashi_shop.Model.Product;
import com.example.fashi_shop.Presenter.SanPhamTheoDanhMuc.PresenterLogicSanPhamTheoDanhMuc;
import com.example.fashi_shop.R;
import com.example.fashi_shop.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class SanPhamTheoDanhMucActivity extends AppCompatActivity implements ViewSanPhamTheoDanhMuc, View.OnClickListener {
    RecyclerView recyclerViewSanPhamTheoDanhMuc;
    Button btnChangeViewList;
    boolean gridViewProduct = true;
    RecyclerView.LayoutManager layoutManager;
    PresenterLogicSanPhamTheoDanhMuc sanPhamTheoDanhMuc;
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

        sanPhamTheoDanhMuc = new PresenterLogicSanPhamTheoDanhMuc(this);
        sanPhamTheoDanhMuc.LayDanhSachSanPham(categoriesId);
        btnChangeViewList.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        return true;
    }

    @Override
    public void HienThiDanhSachSanPham(List<Product> products) {
        if (gridViewProduct) {
            layoutManager = new GridLayoutManager(SanPhamTheoDanhMucActivity.this, 2);
            productAdapter = new ProductAdapter(this, products, R.layout.item_product, id -> {
                Intent intent = new Intent(getApplicationContext(), ChiTietSanPhamActivity.class);
                intent.putExtra("productID",id);
                startActivity(intent);
            });
        } else {
            layoutManager = new LinearLayoutManager(SanPhamTheoDanhMucActivity.this);
            productAdapter = new ProductAdapter(this, products, R.layout.custom_layout_list_product, id -> {
                Intent intent = new Intent(getApplicationContext(), ChiTietSanPhamActivity.class);
                intent.putExtra("productID",id);
                startActivity(intent);
            });
        }
        recyclerViewSanPhamTheoDanhMuc.setLayoutManager(layoutManager);
        recyclerViewSanPhamTheoDanhMuc.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void LoiHienThiDanhSachSanPham() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnChangeViewList:
            gridViewProduct = !gridViewProduct;
            sanPhamTheoDanhMuc.LayDanhSachSanPham(categoriesId);
            ;break;
        }
    }

}
