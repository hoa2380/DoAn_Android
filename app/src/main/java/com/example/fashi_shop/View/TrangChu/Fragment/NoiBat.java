package com.example.fashi_shop.View.TrangChu.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashi_shop.Adapter.ProductAdapter;
import com.example.fashi_shop.R;
import com.example.fashi_shop.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.example.fashi_shop.responses.ProductsResponse;
import com.example.fashi_shop.service.ProductService;
import com.example.fashi_shop.utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoiBat extends Fragment {
    RecyclerView recyclerViewNoiBat;
    ProductService productService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_noibat, container, false);

        recyclerViewNoiBat = view.findViewById(R.id.recyclerViewNoiBat);
        productService = ApiClient.getProductService();
        loadProducts();
        return view;
    }

    public void loadProducts() {
        productService.getProducts().enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful()) {
                    recyclerViewNoiBat.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    //item change position when fast scroll up
                    recyclerViewNoiBat.setItemAnimator(null);
                    ProductAdapter productAdapter = new ProductAdapter(getContext(), response.body().products, R.layout.item_product, id -> {
                                            Intent intent = new Intent(getContext(), ChiTietSanPhamActivity.class);
                                            intent.putExtra("productID",id);
                                            startActivity(intent);
                                        });
                    recyclerViewNoiBat.setAdapter(productAdapter);
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
