package com.example.fashi_shop.View.ManHinhChao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fashi_shop.R;
import com.example.fashi_shop.View.TrangChu.TrangChuActivity;


public class ManHinhChaoActivity extends AppCompatActivity {

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
                    Intent login = new Intent(ManHinhChaoActivity.this, TrangChuActivity.class);
                    startActivity(login);
                }
            }
        });
        thread.start();
    }
}