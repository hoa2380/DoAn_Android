package com.example.fashi_shop.utils;


import com.example.fashi_shop.retrofit.RetrofitClient;
import com.example.fashi_shop.service.ProductService;

public  class ApiClient {
<<<<<<< HEAD
    public static final String BASE_URL = "http://85ba66222e88.ngrok.io/DoAnLaravel2020/public/api/";
=======
    public static final String BASE_URL = "http://be1a823fcf13.ngrok.io/DoAnLaravel2020/public/api/";
>>>>>>> 2e0fc2261e3b86c1ebed94c095f3162d23a7f32a

    public static ProductService getProductService() {
        return RetrofitClient.getRetrofit(BASE_URL).create(ProductService.class);
    }
}
