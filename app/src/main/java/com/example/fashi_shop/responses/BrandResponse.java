package com.example.fashi_shop.responses;


import com.example.fashi_shop.Model.Brand;
import com.google.gson.annotations.SerializedName;

public class BrandResponse extends BaseResponse {
    @SerializedName("data")
    public Brand brand;
}
