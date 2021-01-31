package com.example.fashi_shop.Presenter.ChiTietSanPham;

import android.content.Context;

import com.example.fashi_shop.Model.Cart.Cart;
import com.example.fashi_shop.Model.Product;
import com.example.fashi_shop.View.ChiTietSanPham.ViewChiTietSanPham;

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham {

    ViewChiTietSanPham viewChiTietSanPham;
    Cart cart;

    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        this.cart = new Cart();
    }

    @Override
    public void addToCart(Product product, Context context) {
        cart.ConnectSQL(context);
        boolean check = cart.addCartItem(product);
        if (check) {
            viewChiTietSanPham.AddToCartSuccess();
        } else {
            viewChiTietSanPham.AddToCartFail();
        }
    }
}
