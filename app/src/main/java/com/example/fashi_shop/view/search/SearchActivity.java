package com.example.fashi_shop.view.search;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashi_shop.R;
import com.google.android.material.appbar.MaterialToolbar;

public class SearchActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    RecyclerView recyclerSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);

        toolbar = findViewById(R.id.toolBarSearch);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Tìm kiếm");
        setSupportActionBar(toolbar);

        recyclerSearch = findViewById(R.id.recyclerSearch);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem itSearch = menu.findItem(R.id.itSearch);
        SearchView searchView = (SearchView) itSearch.getActionView();
        searchView.setIconified(false);

        return true;
    }
}
