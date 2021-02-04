package com.example.fashi_shop.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fashi_shop.view.home.fragment.khuyen_mai.KhuyenMai;
import com.example.fashi_shop.view.home.fragment.noi_bat.NoiBat;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> listFragments = new ArrayList<Fragment>();
    List<String> titleFragments = new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        listFragments.add(new NoiBat());
        listFragments.add(new KhuyenMai());

        titleFragments.add("Nổi bật");
        titleFragments.add("Khuyến mãi");
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragments.get(position);
    }
}
