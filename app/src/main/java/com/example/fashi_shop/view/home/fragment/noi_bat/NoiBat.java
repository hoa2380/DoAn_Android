package com.example.fashi_shop.view.home.fragment.noi_bat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashi_shop.adapter.ProductAdapter;
import com.example.fashi_shop.R;
import com.example.fashi_shop.model.Product;
import com.example.fashi_shop.presenter.fragment.noi_bat.PresenterNoiBat;
import com.example.fashi_shop.view.product_detail.ProductDetailActivity;

import java.util.List;

public class NoiBat extends Fragment implements ViewNoiBat {
    RecyclerView recyclerViewNoiBat;
    PresenterNoiBat presenterLogicNoiBat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_noibat, container, false);

        recyclerViewNoiBat = view.findViewById(R.id.recyclerViewNoiBat);
        presenterLogicNoiBat = new PresenterNoiBat(this);
        presenterLogicNoiBat.getProducts();

        return view;
    }

    @Override
    public void loadProducts(List<Product> products) {
        recyclerViewNoiBat.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ProductAdapter productAdapter = new ProductAdapter(getContext(), products, R.layout.item_product ,id -> {
            Intent intent = new Intent(getContext(), ProductDetailActivity.class);
            intent.putExtra("productID", id);
            startActivity(intent);
        });
        recyclerViewNoiBat.setAdapter(productAdapter);
    }
}
