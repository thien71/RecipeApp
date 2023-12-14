package com.example.recipe_app;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private int maNguoiDung;
    private int quyen;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, int maNguoiDung, int quyen) {
        super(fragmentActivity);
        this.maNguoiDung = maNguoiDung;
        this.quyen = quyen;
    }
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                Log.d("VIEW THIEN", maNguoiDung + " " + quyen);
                return HomeFragment.newInstance(maNguoiDung, quyen);
            case 1:
                return CommunityFragment.newInstance(maNguoiDung);
            case 2:
                return ProfileFragment.newInstance(maNguoiDung);
            default:
                return new HomeFragment();
        }
    }
    @Override
    public int getItemCount() {
        return 3;
    }
}