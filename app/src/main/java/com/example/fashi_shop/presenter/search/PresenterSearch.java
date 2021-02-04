package com.example.fashi_shop.presenter.search;

import android.util.Log;

import com.example.fashi_shop.model.Product;
import com.example.fashi_shop.responses.ProductsResponse;
import com.example.fashi_shop.service.ProductService;
import com.example.fashi_shop.utils.ApiClient;
import com.example.fashi_shop.view.search.ViewSearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterSearch implements IPresenterSearch{
    ViewSearch viewSearch;
    ProductService productService;

    public PresenterSearch(ViewSearch viewSearch) {
        this.viewSearch = viewSearch;
    }

    @Override
    public void getSearchProducts(String query) {
        productService = ApiClient.getProductService();
        productService.getProductSearch(query).enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful()){
                    List<Product> products = response.body().products;
                    System.out.println(products);
                    viewSearch.searchSuccess(products);
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
