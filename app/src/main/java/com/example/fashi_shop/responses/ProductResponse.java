package com.example.fashi_shop.responses;


import com.example.fashi_shop.Model.Product;
import com.google.gson.annotations.SerializedName;

public class ProductResponse extends BaseResponse {
    @SerializedName("data")
    public Product product;
}


