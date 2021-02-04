package com.example.fashi_shop.presenter.product_detail;

import android.content.Context;

import com.example.fashi_shop.model.Product;

public interface IPresenterProductDetail {
    void getProduct(long id);
    void getCategory(long id);
    void getBrand(long id);
    void addToCart(Product product, Context context);
}
