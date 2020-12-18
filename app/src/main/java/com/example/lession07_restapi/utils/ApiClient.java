package com.example.lession07_restapi.utils;

import com.example.lession07_restapi.retrofit.RetrofitClient;
import com.example.lession07_restapi.service.ProductService;

public  class ApiClient {
    public static final String BASE_URL = "http://10.0.2.2:8000/api/";

    public static ProductService getProductService() {
        return RetrofitClient.getRetrofit(BASE_URL).create(ProductService.class);
    }
}
