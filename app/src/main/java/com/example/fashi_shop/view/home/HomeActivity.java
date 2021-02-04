package com.example.fashi_shop.view.home;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.fashi_shop.adapter.CategoryAdapter;
import com.example.fashi_shop.adapter.ViewPagerAdapter;
import com.example.fashi_shop.model.Category;
import com.example.fashi_shop.presenter.product_detail.PresenterProductDetail;
import com.example.fashi_shop.presenter.home.handle_menu.PresenterHandleMenu;
import com.example.fashi_shop.R;
import com.example.fashi_shop.view.cart.CartActivity;
import com.example.fashi_shop.view.product_by_categories.ProductByCategoriesActivity;
import com.example.fashi_shop.view.search.SearchActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements ViewHandleMenu, View.OnClickListener {

    MaterialToolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ListView listView;
    TextView txtSoLuongSanPhamGioHang;
    Button btnSearch;
    boolean onPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        toolbar = findViewById(R.id.toolBarTrangChu);
        tabLayout = findViewById(R.id.tabLayoutTrangChu);
        viewPager = findViewById(R.id.viewPagerTrangChu);
        drawerLayout = findViewById(R.id.drawerLayout);
        listView = findViewById(R.id.lvMenu);
        btnSearch = findViewById(R.id.btnSearch);

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

        PresenterHandleMenu logicXuLyMenu = new PresenterHandleMenu(this);

        logicXuLyMenu.getMenuList();
        btnSearch.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        PresenterProductDetail logicChiTietSanPham = new PresenterProductDetail();
        getMenuInflater().inflate(R.menu.menu_home, menu);

        MenuItem itemGioHang = menu.findItem(R.id.itemGioHang);
        View viewCustomGioHang = itemGioHang.getActionView();
        viewCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(HomeActivity.this, CartActivity.class);
               startActivity(intent);
            }
        });
        txtSoLuongSanPhamGioHang = viewCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);
        txtSoLuongSanPhamGioHang.setText(String.valueOf(logicChiTietSanPham.countProductCart(this)));
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
    public void LoadMenuList(List<Category> categories) {
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, R.layout.item_category, categories);
        listView.setAdapter(categoryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("id", categories.get(position).id.toString());
                Intent intent = new Intent(getApplicationContext(), ProductByCategoriesActivity.class);
                intent.putExtra("categoriesID", categories.get(position).id);
                intent.putExtra("categoryName", categories.get(position).name);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (onPause){
            PresenterProductDetail logicChiTietSanPham = new PresenterProductDetail();
            txtSoLuongSanPhamGioHang.setText(String.valueOf(logicChiTietSanPham.countProductCart(this)));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btnSearch:
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }

    }
}