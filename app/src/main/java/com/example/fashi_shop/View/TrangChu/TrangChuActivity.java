package com.example.fashi_shop.View.TrangChu;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.fashi_shop.Adapter.CategoryAdapter;
import com.example.fashi_shop.Adapter.ViewPagerAdapter;
import com.example.fashi_shop.Model.Category;
import com.example.fashi_shop.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import com.example.fashi_shop.R;
import com.example.fashi_shop.View.SanPhamTheoDanhMuc.SanPhamTheoDanhMucActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu{

    MaterialToolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        toolbar = findViewById(R.id.toolBarTrangChu);
        tabLayout = findViewById(R.id.tabLayoutTrangChu);
        viewPager = findViewById(R.id.viewPagerTrangChu);
        drawerLayout = findViewById(R.id.drawerLayout);
        listView = findViewById(R.id.lvMenu);


        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.grey));
        drawerToggle.syncState();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        PresenterLogicXuLyMenu logicXuLyMenu = new PresenterLogicXuLyMenu(this);
        logicXuLyMenu.LayDanhSachMenu();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }


    @Override
    public void HienThiDanhSachMenu(List<Category> categoryList) {
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, R.layout.item_category, categoryList);
        listView.setAdapter(categoryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("id", categoryList.get(position).id.toString());
                Intent intent = new Intent(getApplicationContext(), SanPhamTheoDanhMucActivity.class);
                intent.putExtra("categoriesID", categoryList.get(position).id);
                intent.putExtra("categoryName", categoryList.get(position).name);
                startActivity(intent);
            }
        });
    }
}