package com.example.recipe_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ProfilePagerAdapter extends FragmentStateAdapter {

    public ProfilePagerAdapter(@NonNull ProfileFragment fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SavedFragment();
            case 1:
                return new CookbooksFragment();
            case 2:
                return new ActivityFragment();
            default:
                return new SavedFragment();
        }
    }
    @Override
    public int getItemCount() {
        return 3;
    }
}