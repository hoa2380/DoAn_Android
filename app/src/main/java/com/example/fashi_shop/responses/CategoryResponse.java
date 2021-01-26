package com.example.fashi_shop.responses;

import com.example.fashi_shop.Model.Category;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse extends BaseResponse{
    @SerializedName("data")
    public Category category;
}
