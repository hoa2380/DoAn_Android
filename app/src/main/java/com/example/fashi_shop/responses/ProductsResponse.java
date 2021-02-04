package com.example.fashi_shop.responses;


import com.example.fashi_shop.model.Product;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponse {
    @SerializedName("data")
    public List<Product> products;
}
