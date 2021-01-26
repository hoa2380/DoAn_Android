package com.example.fashi_shop.service;



import com.example.fashi_shop.responses.BrandResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BrandService {
    @GET("brands")
    Call<BrandResponse> getBrands();

    @GET("brands/{id}")
    Call<BrandResponse> getBrands(@Path("id") long id);
}
