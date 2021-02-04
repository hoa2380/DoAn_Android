package com.example.fashi_shop.view.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fashi_shop.R;
import com.example.fashi_shop.adapter.ViewPagerAdapterLogin;
import com.example.fashi_shop.presenter.login.PresenterHandleLogin;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity implements ViewHandleLogin, View.OnClickListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    MaterialToolbar toolbar;


    Button btnLogin;
    EditText edtUserName, edtPassword;
    PresenterHandleLogin logicXuLyDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        tabLayout = findViewById(R.id.tabLogin);
        viewPager = findViewById(R.id.viewPagerLogin);
        toolbar = findViewById(R.id.toolBarLogin);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Đăng nhập/Đăng ký");
        setSupportActionBar(toolbar);

        ViewPagerAdapterLogin viewPagerAdapterLogin = new ViewPagerAdapterLogin(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterLogin);
        viewPagerAdapterLogin.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);

        logicXuLyDangNhap = new PresenterHandleLogin(this);
    }

    @Override
    public void DangNhapThanhCong(String thongbao) {
        Toast.makeText(LoginActivity.this, "Login Success! "+ thongbao, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangNhapThatBai() {
        Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        String userName, passWord;

        userName = edtUserName.getText().toString();
        passWord = edtPassword.getText().toString();

        logicXuLyDangNhap.KiemTraDangNhap(userName, passWord);
    }
}