package com.example.fashi_shop.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fashi_shop.view.login.fragment.FragmentLogin;
import com.example.fashi_shop.view.login.fragment.FragmentSignup;

public class ViewPagerAdapterLogin extends FragmentPagerAdapter {

    public ViewPagerAdapterLogin(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                FragmentLogin fragmentLogin = new FragmentLogin();
                return fragmentLogin;
            case 1 :
                FragmentSignup fragmentSignup = new FragmentSignup();
                return fragmentSignup;

            default: return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return "Đăng nhập";
            case 1 :
                return "Đăng ký";

            default: return null;
        }
    }
}
