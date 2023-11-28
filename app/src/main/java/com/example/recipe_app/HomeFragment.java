package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class HomeFragment extends Fragment {

    private RecyclerView rcvHomeRecommended;
    private RecyclerView rcvHomeCommunity;
    private RecyclerView rcvHomeTreding;

    private HomeRecommendedAdapter homeRecommendedAdapter;
    private HomeCommunityAdapter homeCommunityAdapter;
    private HomeTrendingAdapter homeTrendingAdapter;

    private LinearLayout search_bar, home_chat;

    private RecyclerViewItemClickListener itemClickListener;
    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rcvHomeRecommended = (RecyclerView) view.findViewById(R.id.rcvRecommended);
        rcvHomeCommunity = (RecyclerView) view.findViewById(R.id.rcvHomeCommunity);
        rcvHomeTreding = (RecyclerView) view.findViewById(R.id.rcvTrending);

        search_bar = (LinearLayout) view.findViewById(R.id.search_bar);
        home_chat = (LinearLayout) view.findViewById(R.id.home_chat);

        //Toast.makeText(getContext(), getHomeRecommendedList().get(0).getTen(), Toast.LENGTH_SHORT).show();


        // Home Chat
        home_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatboxActivity.class);
                startActivity(intent);
            }
        });

        //Home Search
        search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        // Home Recommended
        homeRecommendedAdapter = new HomeRecommendedAdapter();

        int soCot = 4;
        GridLayoutManager homeRecommendedGridLayoutManager = new GridLayoutManager (getActivity(), soCot);
        rcvHomeRecommended.setLayoutManager(homeRecommendedGridLayoutManager);
        rcvHomeRecommended.setFocusable(false);
        rcvHomeRecommended.setNestedScrollingEnabled(false);

        homeRecommendedAdapter.setHomeRecommendedList(getHomeRecommendedList());

        rcvHomeRecommended.setAdapter(homeRecommendedAdapter);
        homeRecommendedAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                HomeRecommended selectHomeRecommended = homeRecommendedAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);

                intent.putExtra("maCongThuc", selectHomeRecommended.getMaCongThuc());

                startActivity(intent);
            }
        });

        // Home Community

        homeCommunityAdapter = new HomeCommunityAdapter();

        LinearLayoutManager homeCommunityLinearLayoutManager = new LinearLayoutManager(getActivity());

        homeCommunityLinearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rcvHomeCommunity.setLayoutManager(homeCommunityLinearLayoutManager);
        rcvHomeCommunity.setFocusable(false);
        rcvHomeCommunity.setNestedScrollingEnabled(false);

        homeCommunityAdapter.setHomeCommunityList(getHomeCommunityList());

        rcvHomeCommunity.setAdapter(homeCommunityAdapter);

        homeCommunityAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });

        // My Trending
        homeTrendingAdapter = new HomeTrendingAdapter();

        LinearLayoutManager homeTrendingLinearLayoutManager = new LinearLayoutManager(getActivity());

        homeTrendingLinearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rcvHomeTreding.setLayoutManager(homeTrendingLinearLayoutManager);
        rcvHomeTreding.setFocusable(false);
        rcvHomeTreding.setNestedScrollingEnabled(false);

        homeTrendingAdapter.setHomeTrending(getHomeTrendingList(3));

        rcvHomeTreding.setAdapter(homeTrendingAdapter);

        homeTrendingAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private List<HomeRecommended> getHomeRecommendedList() {
        List<HomeRecommended> arrayHomeRecommendedList = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("CongThuc");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    int maCongThuc = snapshot.child("maCongThuc").getValue(Integer.class);
                    String hinh = snapshot.child("duongDanHinhAnh").getValue(String.class);
                    String tieuDe = snapshot.child("tieuDe").getValue(String.class);

                    HomeRecommended recommended = new HomeRecommended(maCongThuc, hinh, tieuDe);
                    arrayHomeRecommendedList.add(recommended);
                }
                homeRecommendedAdapter.setHomeRecommendedList(arrayHomeRecommendedList);
                homeRecommendedAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return arrayHomeRecommendedList;
    }
    private List<Community> getHomeCommunityList() {
        List<Community> arrayHomeCommunityList = new ArrayList<>();

        arrayHomeCommunityList.add(new Community((R.drawable.img_banh_canh_ca_loc), "Bánh canh cá lóc"));
        arrayHomeCommunityList.add(new Community((R.drawable.img_lau_tha_phan_thiet), "Lẩu thả Phan Thiết"));
        arrayHomeCommunityList.add(new Community((R.drawable.img_com_ga_hoi_an), "Cơm gà Hội An"));
        arrayHomeCommunityList.add(new Community((R.drawable.img_goi_ca_nam_o), "Gỏi cá Nam Ô"));
        arrayHomeCommunityList.add(new Community((R.drawable.img_canh_kho_qua), "Canh khổ qua"));
        arrayHomeCommunityList.add(new Community((R.drawable.img_ca_bo_lat_rim), "Cá bò lát rim"));

        return arrayHomeCommunityList;
    }
    private List<HomeRecommended> getHomeTrendingList(int soMuc) {
        List<HomeRecommended> arrayHomeTrendingList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("CongThuc");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<HomeRecommended> fullList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String hinh = snapshot.child("duongDanHinhAnh").getValue(String.class);
                    String tieuDe = snapshot.child("tieuDe").getValue(String.class);

                    HomeRecommended recommended = new HomeRecommended(hinh, tieuDe);
                    fullList.add(recommended);
                }
                int listSize = fullList.size();

                if (listSize > 0) {
                    Random random = new Random();
                    Set<Integer> selectedIndexes = new HashSet<>();

                    while (selectedIndexes.size() < soMuc) {
                        int randomIndex = random.nextInt(listSize);
                        selectedIndexes.add(randomIndex);
                    }

                    for (Integer index : selectedIndexes) {
                        arrayHomeTrendingList.add(fullList.get(index));
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return arrayHomeTrendingList;
    }
}