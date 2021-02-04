package com.example.fashi_shop.responses;

import com.example.fashi_shop.model.Category;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesResponse {
    @SerializedName("data")
    public List<Category> categories;
}
