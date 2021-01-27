package com.example.fashi_shop.utils;


import com.example.fashi_shop.retrofit.RetrofitClient;
import com.example.fashi_shop.service.BrandService;
import com.example.fashi_shop.service.CategoryService;
import com.example.fashi_shop.service.ProductService;

public  class ApiClient {
    public static final String BASE_URL = "http://192.168.1.12/DoAnLaravel2020/public/api/";

    public static ProductService getProductService() {
        return RetrofitClient.getRetrofit(BASE_URL).create(ProductService.class);
    }

    public static BrandService getBrandService() {
        return RetrofitClient.getRetrofit(BASE_URL).create(BrandService.class);
    }

    public static CategoryService getCategoryService(){
        return RetrofitClient.getRetrofit(BASE_URL).create(CategoryService.class);
    }
}
