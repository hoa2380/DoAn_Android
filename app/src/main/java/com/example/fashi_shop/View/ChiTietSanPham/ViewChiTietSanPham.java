package com.example.fashi_shop.View.ChiTietSanPham;

import com.example.fashi_shop.Model.Product;

public interface ViewChiTietSanPham {
    void LoadProduct(Product product);
    void AddToCartSuccess();
    void AddToCartFail();
}
