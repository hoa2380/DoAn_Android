package com.example.fashi_shop.view.product_by_categories;

import com.example.fashi_shop.model.Product;

import java.util.List;

public interface ViewProductByCategories {
    void loadProducts(List<Product> products);
    void errorLoadProducts();
}
