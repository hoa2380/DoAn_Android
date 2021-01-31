package com.example.fashi_shop.service;



import com.example.fashi_shop.Model.Product;
import com.example.fashi_shop.responses.ProductResponse;
import com.example.fashi_shop.responses.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {
    @GET("products")
    Call<ProductsResponse> getProducts();

    @GET("products/{id}")
    Call<ProductResponse> getProduct(@Path("id") long id);

    @GET("getProductsByCategories/{id}")
    Call<ProductsResponse> getProductsByCategories(@Path("id") long id);

    @POST("products/")
    Call<Void> createProduct(@Body Product product);

    @PUT("products/{id}")
    Call<Void> updateProduct(@Body Product product, @Path("id") int id);

    @DELETE("products/{id}")
    Call<Void> deleteProduct(@Path("id") int id);
}
