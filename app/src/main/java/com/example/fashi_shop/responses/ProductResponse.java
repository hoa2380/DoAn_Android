package com.example.fashi_shop.responses;


import com.example.fashi_shop.model.Product;
import com.google.gson.annotations.SerializedName;

public class ProductResponse {
    @SerializedName("data")
    public Product product;
}


