package com.example.fashi_shop.presenter.fragment.noi_bat;

import android.util.Log;

import com.example.fashi_shop.model.Product;
import com.example.fashi_shop.responses.ProductsResponse;
import com.example.fashi_shop.service.ProductService;
import com.example.fashi_shop.utils.ApiClient;
import com.example.fashi_shop.view.home.fragment.noi_bat.ViewNoiBat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterNoiBat implements IPresenterNoiBat{
    ProductService productService;
    ViewNoiBat viewNoiBat;

    public PresenterNoiBat(ViewNoiBat viewNoiBat) {
        this.viewNoiBat = viewNoiBat;
    }

    @Override
    public void getProducts() {
        productService = ApiClient.getProductService();
        productService.getProducts().enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful()){
                    List<Product> products = response.body().products;
                    viewNoiBat.loadProducts(products);
                    System.out.println(products);
                } else {
                    Log.e("Code", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Log.e("onFailure: ", t.toString());
            }
        });
    }
}
