package com.example.fashi_shop.presenter.product_by_categories;

import android.util.Log;

import com.example.fashi_shop.model.Product;
import com.example.fashi_shop.view.product_by_categories.ViewProductByCategories;
import com.example.fashi_shop.responses.ProductsResponse;
import com.example.fashi_shop.service.ProductService;
import com.example.fashi_shop.utils.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterProductByCategories implements IPresenterProductByCategories {

    ProductService productService;

    ViewProductByCategories viewSanPhamTheoDanhMuc;

    public PresenterProductByCategories(ViewProductByCategories viewSanPhamTheoDanhMuc) {
        this.viewSanPhamTheoDanhMuc = viewSanPhamTheoDanhMuc;
    }

    @Override
    public void getProducts(int id){
        productService = ApiClient.getProductService();
        productService.getProductsByCategories(id).enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful()){
                    List<Product> products = response.body().products;
                    viewSanPhamTheoDanhMuc.loadProducts(products);
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
