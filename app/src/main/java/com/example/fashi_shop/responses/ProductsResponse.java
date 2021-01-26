package com.example.fashi_shop.responses;


import com.example.fashi_shop.Model.Product;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponse extends BaseResponse {
    @SerializedName("data")
    public List<Product> products;
}
