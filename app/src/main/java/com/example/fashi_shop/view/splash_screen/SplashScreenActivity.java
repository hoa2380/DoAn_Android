package com.example.fashi_shop.view.splash_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fashi_shop.R;
import com.example.fashi_shop.view.home.HomeActivity;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {

                } finally {
                    Intent login = new Intent(SplashScreenActivity.this, HomeActivity.class);
                    startActivity(login);
                }
            }
        });
        thread.start();
    }
}