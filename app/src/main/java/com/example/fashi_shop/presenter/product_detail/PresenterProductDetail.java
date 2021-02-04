package com.example.fashi_shop.presenter.product_detail;

import android.content.Context;
import android.util.Log;

import com.example.fashi_shop.model.Brand;
import com.example.fashi_shop.model.Category;
import com.example.fashi_shop.model.cart.Cart;
import com.example.fashi_shop.model.Product;
import com.example.fashi_shop.responses.BrandResponse;
import com.example.fashi_shop.responses.CategoryResponse;
import com.example.fashi_shop.responses.ProductResponse;
import com.example.fashi_shop.service.BrandService;
import com.example.fashi_shop.service.CategoryService;
import com.example.fashi_shop.service.ProductService;
import com.example.fashi_shop.utils.ApiClient;
import com.example.fashi_shop.view.product_detail.ViewProductDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterProductDetail implements IPresenterProductDetail {

    ProductService productService;
    BrandService brandService;
    CategoryService categoryService;

    ViewProductDetail viewProductDetail;
    Cart cart;

    public PresenterProductDetail() {
        this.cart = new Cart();
    }

    public PresenterProductDetail(ViewProductDetail viewChiTietSanPham) {
        this.viewProductDetail = viewChiTietSanPham;
        this.cart = new Cart();
    }

    @Override
    public void getProduct(long id) {
        productService = ApiClient.getProductService();
        productService.getProduct(id).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    Product product = response.body().product;
                    viewProductDetail.showProduct(product);
                } else {
                    Log.e("Code", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.e("onFailure: ", t.toString());
            }
        });

    }

    @Override
    public void getCategory(long id) {
        categoryService = ApiClient.getCategoryService();
        categoryService.getCategory(id).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    Category category = response.body().category;
                    viewProductDetail.showCategory(category);
                    System.out.println(category);
                } else {
                    Log.e("Code", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.e("onFailure: ", t.toString());
            }
        });
    }

    @Override
    public void getBrand(long id) {
        brandService = ApiClient.getBrandService();
        brandService.getBrands(id).enqueue(new Callback<BrandResponse>() {
            @Override
            public void onResponse(Call<BrandResponse> call, Response<BrandResponse> response) {
                if (response.isSuccessful()) {
                    Brand brand = response.body().brand;
                    viewProductDetail.showBrand(brand);
                    System.out.println(brand);
                } else {
                    Log.e("Code", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<BrandResponse> call, Throwable t) {
                Log.e("onFailure: ", t.toString());
            }
        });
    }

    @Override
    public void addToCart(Product product, Context context) {
        cart.ConnectSQL(context);
        boolean check = cart.addCartItem(product);
        if (check) {
            viewProductDetail.addToCartSuccess();
        } else {
            viewProductDetail.addToCartFail();
        }
    }

    public int countProductCart(Context context) {
        cart.ConnectSQL(context);
        List<Product> products = cart.loadProductCart();

        int count = products.size();

        return count;
    }
}
