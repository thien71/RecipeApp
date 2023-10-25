package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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

                intent.putExtra("idTenMon", selectHomeRecommended.getTen());
                intent.putExtra("idHinh", selectHomeRecommended.getHinh());

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

        homeTrendingAdapter.setHomeTrending(getHomeTrendingList());

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
        List<HomeRecommended> arrayHomeRecommendeds = new ArrayList<>();

        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_my_quang), "Mỳ Quảng"));
        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_banh_mi_cuon_xuc_xich), "Bánh mì xúc xích"));
        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_sup_ga), "Súp gà"));
        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_sua_chuoi), "Sữa chuối"));
        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_ngheu_hap_xa), "Ngheo hấp xả"));
        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_dau_trang_ham_thit), "Đậu hầm thịt"));
        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_bun_mam_nem_thit_luoc), "Bún mắm thịt luộc"));
        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_chao_hen_xao_cay), "Cháo hến"));
        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_banh_chuoi), "Bánh chuối"));
        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_banh_can), "Bánh căn"));
        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_canh_rau), "Canh rau"));
        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_banh_canh_he_phu_yen), "Bánh canh Phú Yên"));
        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_banh_xeo), "Bánh xèo"));
        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_bun_bo_hue), "Bún bò Huế"));
        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_cao_lau), "Cao lầu"));
        arrayHomeRecommendeds.add(new HomeRecommended((R.drawable.img_com_ga_hoi_an), "Cơm gà"));

        return arrayHomeRecommendeds;
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
    private List<Community> getHomeTrendingList() {
        List<Community> arrayHomeTrendingList = new ArrayList<>();

        arrayHomeTrendingList.add(new Community((R.drawable.img_com_hen), "Cơm hến"));
        arrayHomeTrendingList.add(new Community((R.drawable.img_mieng_luon), "Miếng lươn"));
        arrayHomeTrendingList.add(new Community((R.drawable.img_nem_nuong_nha_trang), "Nem nướng"));
        arrayHomeTrendingList.add(new Community((R.drawable.img_che_bap), "Chè bắp"));
        arrayHomeTrendingList.add(new Community((R.drawable.img_banh_beo), "Bánh bèo"));

        return arrayHomeTrendingList;
    }
}