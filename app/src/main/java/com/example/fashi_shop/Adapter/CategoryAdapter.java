package com.example.fashi_shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.fashi_shop.Model.Category;
import com.example.fashi_shop.R;


import java.util.List;


public class CategoryAdapter extends ArrayAdapter<Category> {

    Context context;
    List<Category> categoryList;
    int layoutResource;

    public CategoryAdapter(Context context, int resource, List<Category> categoryList) {
        super(context, resource, categoryList);
        this.context = context;
        this.categoryList = categoryList;
        this.layoutResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layoutResource,null);

        TextView tvCategory = convertView.findViewById(R.id.tvCategory);
        tvCategory.setText(categoryList.get(position).name);
        return convertView;
    }
}
