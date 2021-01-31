package com.example.fashi_shop.responses;

import com.example.fashi_shop.Model.Category;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesResponse extends BaseResponse{
    @SerializedName("data")
    public List<Category> categories;
}
