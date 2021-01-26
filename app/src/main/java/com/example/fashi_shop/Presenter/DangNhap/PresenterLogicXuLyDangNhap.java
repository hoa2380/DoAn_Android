package com.example.fashi_shop.Presenter.DangNhap;

import com.example.fashi_shop.View.DangNhap.ViewXuLyDangNhap;

public class PresenterLogicXuLyDangNhap implements PresenterImplementXuLyDangNhap {

    ViewXuLyDangNhap viewXuLyDangNhap;
    public PresenterLogicXuLyDangNhap(ViewXuLyDangNhap viewXuLyDangNhap){
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
