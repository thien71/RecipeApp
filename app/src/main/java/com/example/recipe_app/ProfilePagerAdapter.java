package com.example.recipe_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ProfilePagerAdapter extends FragmentStateAdapter {
    private int maNguoiDung;

    public ProfilePagerAdapter(@NonNull ProfileFragment fragmentActivity, int maNguoiDung) {
        super(fragmentActivity);
        this.maNguoiDung = maNguoiDung;
    }
    public ProfilePagerAdapter(@NonNull ProfileFragment fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return SavedFragment.newInstance(maNguoiDung);
            case 1:
                return CookbooksFragment.newInstance(maNguoiDung);
            case 2:
                return ActivityFragment.newInstance(maNguoiDung);
            default:
                return new SavedFragment();
        }
    }
    @Override
    public int getItemCount() {
        return 3;
    }
}