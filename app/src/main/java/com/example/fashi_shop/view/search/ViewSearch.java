package com.example.fashi_shop.view.search;

import com.example.fashi_shop.model.Product;

import java.util.List;

public interface ViewSearch {
    void searchSuccess(List<Product> products);
    void searchFailure();
}
