package com.example.fashi_shop.service;

import com.example.fashi_shop.responses.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {
    @GET("categories")
    Call<CategoryResponse> getCategories();

    @GET("categories/{id}")
    Call<CategoryResponse> getCategories(@Path("id") long id);
}
