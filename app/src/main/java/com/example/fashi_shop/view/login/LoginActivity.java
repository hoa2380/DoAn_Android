package com.example.fashi_shop.view.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fashi_shop.R;
import com.example.fashi_shop.presenter.login.PresenterHandleLogin;

public class LoginActivity extends AppCompatActivity implements ViewHandleLogin, View.OnClickListener {

    Button btnLogin;
    EditText edtUserName, edtPassword;
    PresenterHandleLogin logicXuLyDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassWord);

        btnLogin.setOnClickListener(this);
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