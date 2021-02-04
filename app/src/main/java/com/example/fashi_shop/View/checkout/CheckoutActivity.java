package com.example.fashi_shop.View.checkout;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fashi_shop.R;
import com.google.android.material.appbar.MaterialToolbar;

public class CheckoutActivity extends AppCompatActivity {
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_checkout);

        toolbar = findViewById(R.id.toolBarThanhToan);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Thanh toán");
        setSupportActionBar(toolbar);
    }
}
