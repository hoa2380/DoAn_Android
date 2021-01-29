package com.example.fashi_shop.Presenter.TrangChu.XuLyMenu;

import android.util.Log;

import com.example.fashi_shop.Model.Category;
import com.example.fashi_shop.View.TrangChu.ViewXuLyMenu;
import com.example.fashi_shop.responses.CategoriesResponse;
import com.example.fashi_shop.responses.CategoryResponse;
import com.example.fashi_shop.service.CategoryService;
import com.example.fashi_shop.utils.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicXuLyMenu implements PresenterXuLyMenu {

    CategoryService categoryService;

    ViewXuLyMenu viewXuLyMenu;
    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu){
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void LayDanhSachMenu() {

        categoryService = ApiClient.getCategoryService();
        categoryService.getCategories().enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                if (response.isSuccessful()){
                   List<Category> categories = response.body().categories;
                   viewXuLyMenu.HienThiDanhSachMenu(categories);
                }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {

            }
        });
    }
}
