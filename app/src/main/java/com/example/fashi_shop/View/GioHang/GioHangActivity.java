package com.example.fashi_shop.View.GioHang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashi_shop.Adapter.AdapterGioHang;
import com.example.fashi_shop.Model.Product;
import com.example.fashi_shop.Presenter.GioHang.PresenterLogicGioHang;
import com.example.fashi_shop.R;
import com.example.fashi_shop.View.checkout.CheckoutActivity;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang, View.OnClickListener {
    RecyclerView recyclerView;
    PresenterLogicGioHang presenterLogicGioHang;
    MaterialToolbar toolbar;
    Button btnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);

        toolbar = findViewById(R.id.toolBarGioHang);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Giỏ hàng");
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerGioHang);
        btnBuy = findViewById(R.id.btnBuy);
        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterLogicGioHang.loadProductCart(this);
        btnBuy.setOnClickListener(this);
    }

    @Override
    public void loadProductCart(List<Product> products) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this, products, R.layout.custom_layout_item_giohang);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);
        System.out.println(products);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnBuy:
                Intent intent = new Intent(GioHangActivity.this, CheckoutActivity.class);
                startActivity(intent);
                break;
        }
    }
}
