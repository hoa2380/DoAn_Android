package com.example.fashi_shop.responses;

import com.example.fashi_shop.model.Category;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse {
    @SerializedName("data")
    public Category category;
}
