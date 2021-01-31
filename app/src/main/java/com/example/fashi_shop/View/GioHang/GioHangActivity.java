package com.example.fashi_shop.View.GioHang;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashi_shop.Adapter.AdapterGioHang;
import com.example.fashi_shop.Model.Product;
import com.example.fashi_shop.Presenter.GioHang.PresenterLogicGioHang;
import com.example.fashi_shop.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang{
    RecyclerView recyclerView;
    PresenterLogicGioHang presenterLogicGioHang;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);

        toolbar = findViewById(R.id.toolBarGioHang);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Giỏ hàng");
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerGioHang);
        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterLogicGioHang.loadProductCart(this);
    }

    @Override
    public void loadProductCart(List<Product> products) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this, products, R.layout.custom_layout_item_giohang);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);
        System.out.println(products);
    }
}
