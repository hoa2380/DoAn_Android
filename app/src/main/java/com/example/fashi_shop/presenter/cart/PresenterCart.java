package com.example.fashi_shop.presenter.cart;

import android.content.Context;

import com.example.fashi_shop.model.cart.Cart;
import com.example.fashi_shop.model.Product;
import com.example.fashi_shop.view.cart.ViewCart;

import java.util.List;

public class PresenterCart implements IPresenterCart {
    Cart cart;
    ViewCart viewCart;

    public PresenterCart(ViewCart viewCart) {
        this.cart = new Cart();
        this.viewCart = viewCart;
    }

    @Override
    public void loadProductCart(Context context) {
        cart.ConnectSQL(context);
        List<Product> products = cart.loadProductCart();
        if (products.size() > 0) {
            viewCart.loadProductCart(products);
        }else {

        }
    }
}
