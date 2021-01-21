package com.example.fashi_shop.service;



import com.example.fashi_shop.responses.BrandResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BrandService {
    @GET("brand")
    Call<BrandResponse> getBrand();
}
