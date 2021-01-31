package com.example.fashi_shop.Presenter.GioHang;

import android.content.Context;

import com.example.fashi_shop.Model.Cart.Cart;
import com.example.fashi_shop.Model.Product;
import com.example.fashi_shop.View.GioHang.ViewGioHang;

import java.util.List;

public class PresenterLogicGioHang implements IPresenterGioHang{
    Cart cart;
    ViewGioHang viewGioHang;

    public PresenterLogicGioHang(ViewGioHang viewGioHang) {
        this.cart = new Cart();
        this.viewGioHang = viewGioHang;
    }

    @Override
    public void loadProductCart(Context context) {
        cart.ConnectSQL(context);
        List<Product> products = cart.loadProductCart();
        if (products.size() > 0) {
            viewGioHang.loadProductCart(products);
        }else {

        }
    }
}
