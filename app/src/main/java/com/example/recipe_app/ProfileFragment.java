package com.example.recipe_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileFragment extends Fragment {

    private BottomNavigationView profileNav;
    private ViewPager2 profileViewPager2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileNav = (BottomNavigationView) view.findViewById(R.id.profile_bottom_nav);
        profileViewPager2 = (ViewPager2) view.findViewById(R.id.profile_view_pager2);

        setUpProfileViewPager2();

        return view;
    }
    private void setUpProfileViewPager2() {
        ProfilePagerAdapter adapter = new ProfilePagerAdapter(this);
        profileViewPager2.setAdapter(adapter);

        profileViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        profileNav.getMenu().findItem(R.id.action_profile_saved).setChecked(true);
                        break;
                    case 1:
                        profileNav.getMenu().findItem(R.id.action_profile_cookbooks).setChecked(true);
                        break;
                    case 2:
                        profileNav.getMenu().findItem(R.id.action_profile_activity).setChecked(true);
                        break;
                }
            }
        });
        profileNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_profile_saved) {
                    profileViewPager2.setCurrentItem(0);
                } else if (item.getItemId() == R.id.action_profile_cookbooks) {
                    profileViewPager2.setCurrentItem(1);
                } else if (item.getItemId() == R.id.action_profile_activity) {
                    profileViewPager2.setCurrentItem(2);
                }
                return true;
            }
        });
    }
}