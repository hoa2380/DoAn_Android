package com.example.fashi_shop.view.cart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashi_shop.adapter.AdapterCart;
import com.example.fashi_shop.model.Product;
import com.example.fashi_shop.presenter.cart.PresenterCart;
import com.example.fashi_shop.R;
import com.example.fashi_shop.view.checkout.CheckoutActivity;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class CartActivity extends AppCompatActivity implements ViewCart, View.OnClickListener {
    RecyclerView recyclerView;
    PresenterCart presenterLogicCart;
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
        presenterLogicCart = new PresenterCart(this);
        presenterLogicCart.loadProductCart(this);
        btnBuy.setOnClickListener(this);
    }

    @Override
    public void loadProductCart(List<Product> products) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterCart adapterGioHang = new AdapterCart(this, products, R.layout.custom_layout_item_giohang);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);
        System.out.println(products);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnBuy:
                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                startActivity(intent);
                break;
        }
    }
}
