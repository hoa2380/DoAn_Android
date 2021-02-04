package com.example.fashi_shop.presenter.login;

import com.example.fashi_shop.view.login.ViewHandleLogin;

public class PresenterHandleLogin implements IPresenterHandleLogin {

    ViewHandleLogin viewXuLyDangNhap;
    public PresenterHandleLogin(ViewHandleLogin viewXuLyDangNhap){
        this.viewXuLyDangNhap = viewXuLyDangNhap;
    }

    @Override
    public void KiemTraDangNhap(String username, String password) {
        if (username.equals("badkem") && password.equals("123")){
            //View login success
            viewXuLyDangNhap.DangNhapThanhCong(username);
        } else {
            //View login failure
            viewXuLyDangNhap.DangNhapThatBai();
        }
    }

}
