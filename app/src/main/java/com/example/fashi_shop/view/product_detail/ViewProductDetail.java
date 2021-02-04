package com.example.fashi_shop.view.product_detail;

import com.example.fashi_shop.model.Brand;
import com.example.fashi_shop.model.Category;
import com.example.fashi_shop.model.Product;

public interface ViewProductDetail {
    void showProduct(Product product);
    void showBrand(Brand brand);
    void showCategory(Category category);
    void addToCartSuccess();
    void addToCartFail();
}
