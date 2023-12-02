package com.example.recipe_app;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mNavigationView;
    private ViewPager2 mViewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        mViewPager2 = (ViewPager2) findViewById(R.id.view_pager);

        setUpViewPager2();

        Intent intent = getIntent();
        int maNguoiDung = intent.getIntExtra("maNguoiDung", 1);
        int quyen = intent.getIntExtra("quyen", 0);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this, maNguoiDung, quyen);
        mViewPager2.setAdapter(adapter);
    }

    private void setUpViewPager2() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        mViewPager2.setAdapter(adapter);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        mNavigationView.getMenu().findItem(R.id.action_discover).setChecked(true);
                        break;
                    case 1:
                        mNavigationView.getMenu().findItem(R.id.action_community).setChecked(true);
                        break;
                    case 2:
                        mNavigationView.getMenu().findItem(R.id.action_profile).setChecked(true);
                        break;
                }
            }
        });
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_discover) {
                    mViewPager2.setCurrentItem(0);
                } else if (item.getItemId() == R.id.action_community) {
                    mViewPager2.setCurrentItem(1);
                } else if (item.getItemId() == R.id.action_profile) {
                    mViewPager2.setCurrentItem(2);
                }
                return true;
            }
        });
    }
}