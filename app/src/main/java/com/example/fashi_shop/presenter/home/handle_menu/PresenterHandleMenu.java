package com.example.fashi_shop.presenter.home.handle_menu;

import android.util.Log;

import com.example.fashi_shop.model.Category;
import com.example.fashi_shop.view.home.ViewHandleMenu;
import com.example.fashi_shop.responses.CategoriesResponse;
import com.example.fashi_shop.service.CategoryService;
import com.example.fashi_shop.utils.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterHandleMenu implements IPresenterHandleMenu {

    CategoryService categoryService;

    ViewHandleMenu viewHandleMenu;
    public PresenterHandleMenu(ViewHandleMenu viewHandleMenu){
        this.viewHandleMenu = viewHandleMenu;
    }

    @Override
    public void getMenuList() {
        categoryService = ApiClient.getCategoryService();
        categoryService.getCategories().enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                if (response.isSuccessful()){
                   List<Category> categories = response.body().categories;
                   viewHandleMenu.LoadMenuList(categories);
                } else {
                    Log.e("Code", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {

            }
        });
    }
}
