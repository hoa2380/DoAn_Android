package com.example.fashi_shop.responses;


import com.example.fashi_shop.model.Brand;
import com.google.gson.annotations.SerializedName;

public class BrandResponse {
    @SerializedName("data")
    public Brand brand;
}
