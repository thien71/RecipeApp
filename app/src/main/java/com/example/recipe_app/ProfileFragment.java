package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.recipe_app.adapter.ProfilePagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    private BottomNavigationView profileNav;
    private ViewPager2 profileViewPager2;
    private ImageButton ibtnSetting, ibtnAuthor;
    TextView txtTenNguoiDung, txtSoLike, txtSoBinhLuan;
    CircleImageView cirAvatar;

    private SwipeRefreshLayout swipeRefreshProfile;
    private LinearLayout myRated, myTips;
    private int maNguoiDung;
    public static ProfileFragment newInstance(int maNguoiDung) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt("maNguoiDung", maNguoiDung);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ibtnSetting = (ImageButton) view.findViewById(R.id.ibtnSetting);
        ibtnAuthor = (ImageButton) view.findViewById(R.id.ibtnAuthor);
        myRated = (LinearLayout) view.findViewById(R.id.profile_rated);
        myTips = (LinearLayout) view.findViewById(R.id.profile_tips);

        txtTenNguoiDung = (TextView) view.findViewById(R.id.txtTenNguoiDungProfile);
        cirAvatar = (CircleImageView) view.findViewById(R.id.cirAvatar);

        txtSoLike = (TextView) view.findViewById(R.id.txtSoLikeProfile);
        txtSoBinhLuan = (TextView) view.findViewById(R.id.txtSoBinhLuanProfile);

        swipeRefreshProfile = view.findViewById(R.id.swipeRefreshProfile);

        swipeRefreshProfile.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadNguoiDungData();
                swipeRefreshProfile.setRefreshing(false);
            }
        });

        ibtnAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AuthorActivity.class);
                startActivity(intent);
            }
        });
        myRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyRatedActivity.class);
                intent.putExtra("maNguoiDung", maNguoiDung);
                startActivity(intent);
            }
        });
        myTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyTipsActivity.class);
                intent.putExtra("maNguoiDung", maNguoiDung);
                startActivity(intent);
            }
        });

        ibtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        //menubottom
        profileNav = (BottomNavigationView) view.findViewById(R.id.profile_bottom_nav);
        profileViewPager2 = (ViewPager2) view.findViewById(R.id.profile_view_pager2);

        setUpProfileViewPager2();

        LoadNguoiDungData();

        ProfilePagerAdapter adapter = new ProfilePagerAdapter(this, maNguoiDung);
        profileViewPager2.setAdapter(adapter);

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
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                if (position == 0) {
//                    SavedFragment savedFragment = getSavedFragment();
//                    if (savedFragment != null) {
//                        savedFragment.onScrollToTop();
//                    }
//                }
//            }
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

    private void LoadNguoiDungData() {
        if (getArguments() != null) {
            maNguoiDung = getArguments().getInt("maNguoiDung", 1);
        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDung").child(String.valueOf(maNguoiDung));
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String avatar = snapshot.child("avatar").getValue(String.class);
                    String tenNguoiDung = snapshot.child("tenNguoiDung").getValue(String.class);

                    Picasso.get().load(avatar).into(cirAvatar);
                    txtTenNguoiDung.setText(tenNguoiDung);

                    DataSnapshot likeSnapshot = snapshot.child("Like");
                    if (likeSnapshot.exists()) {
                        long countLikes = likeSnapshot.getChildrenCount();
                        txtSoLike.setText(String.valueOf(countLikes));
                    }

                    DataSnapshot binhLuanSnapshot = snapshot.child("BinhLuan");
                    if (binhLuanSnapshot.exists()) {
                        long countBinhLuan = binhLuanSnapshot.getChildrenCount();
                        txtSoBinhLuan.setText(String.valueOf(countBinhLuan));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private SavedFragment getSavedFragment() {
        ProfilePagerAdapter adapter = (ProfilePagerAdapter) profileViewPager2.getAdapter();
        if (adapter != null && adapter.getItemCount() > 0) {
            return (SavedFragment) adapter.createFragment(0);
        }
        return null;
    }
}