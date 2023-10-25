package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActivityFragment extends Fragment {

    private RecyclerView rcvMyRated;
    private RecyclerView rcvMyTips;
    private RecyclerView rcvReccentlyViewed;

    private MyRatedAdapter myRatedAdapter;
    private MyTipsAdapter myTipsAdapter;
    private MyRecentlyViewedAdapter myRecentlyViewedAdapter;

    private RecyclerViewItemClickListener itemClickListener;
    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);

        rcvMyRated = (RecyclerView) view.findViewById(R.id.rcvMyRatedProfileActivity);
        rcvMyTips = (RecyclerView) view.findViewById(R.id.rcvMyTipsProfileActivity);
        rcvReccentlyViewed = (RecyclerView) view.findViewById(R.id.rcvRecentlyViewed);

        // my rated
        myRatedAdapter = new MyRatedAdapter();
        LinearLayoutManager myRatedLinearLayoutManager = new LinearLayoutManager(getActivity());

        rcvMyRated.setLayoutManager(myRatedLinearLayoutManager);
        rcvMyRated.setFocusable(false);
        rcvMyRated.setNestedScrollingEnabled(false);

        myRatedAdapter.setMyRatedList(getListMyRated());

        rcvMyRated.setAdapter(myRatedAdapter);

        myRatedAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), MyRatedActivity.class);
                startActivity(intent);
            }
        });

        // my tips
        myTipsAdapter = new MyTipsAdapter();

        LinearLayoutManager myTipsLinearLayoutManager = new LinearLayoutManager(getActivity());

        rcvMyTips.setLayoutManager(myTipsLinearLayoutManager);
        rcvMyTips.setFocusable(false);
        rcvMyTips.setNestedScrollingEnabled(false);

        myTipsAdapter.setMyTipsList(getListMyTips());

        rcvMyTips.setAdapter(myTipsAdapter);

        myTipsAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), MyTipsActivity.class);
                startActivity(intent);
            }
        });

        // my recently viewed

        myRecentlyViewedAdapter = new MyRecentlyViewedAdapter();

        LinearLayoutManager myRecentlyViewedLinearLayoutManager = new LinearLayoutManager(getActivity());

        myRecentlyViewedLinearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rcvReccentlyViewed.setLayoutManager(myRecentlyViewedLinearLayoutManager);
        rcvReccentlyViewed.setFocusable(false);
        rcvReccentlyViewed.setNestedScrollingEnabled(false);

        myRecentlyViewedAdapter.setMyRecentlyViewedList(getListMyRecentlyViewed());

        rcvReccentlyViewed.setAdapter(myRecentlyViewedAdapter);

        myRecentlyViewedAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), getListMyRecentlyViewed().get(position).getTen(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), MyTipsActivity.class);
//                startActivity(intent);
            }
        });

        return view;
    }
    private List<MyRated> getListMyRated() {
        List<MyRated> arrayMyRated = new ArrayList<>();
        arrayMyRated.add(new MyRated((R.drawable.img_sup_banh_bao_chay), "Súp bánh bao chay", "3 giờ trước", R.drawable.like));
        arrayMyRated.add(new MyRated((R.drawable.img_banh_pho_mai), "Bánh phô mai", "5 giờ trước", R.drawable.dislike));

        return arrayMyRated;
    }
    private List<MyTips> getListMyTips() {
        List<MyTips> arrayMyTips = new ArrayList<>();

        arrayMyTips.add(new MyTips("Tôi nghĩ nên để nó sôi thêm 5 phút nữa.", 0, "Súp bánh bao chay", "3 giờ trước"));
        arrayMyTips.add(new MyTips("Tôi nghĩ bạn đã cho thừa đường.", 0, "Bánh phô mai", "8 giờ trước", R.drawable.img_banh_pho_mai));

        return arrayMyTips;
    }

    private List<MyRecentlyViewed> getListMyRecentlyViewed() {
        List<MyRecentlyViewed> arrayMyRencentlyViewed = new ArrayList<>();

        arrayMyRencentlyViewed.add(new MyRecentlyViewed((R.drawable.img_mon_ham), "Món hầm"));
        arrayMyRencentlyViewed.add(new MyRecentlyViewed((R.drawable.img_sup_ga), "Súp gà"));
        arrayMyRencentlyViewed.add(new MyRecentlyViewed((R.drawable.img_carrot_nuong), "Cà rốt nướng"));
        arrayMyRencentlyViewed.add(new MyRecentlyViewed((R.drawable.img_banh_pho_mai), "Bánh phô mai"));
        arrayMyRencentlyViewed.add(new MyRecentlyViewed((R.drawable.img_banh_pho_mai), "Bánh phô mai"));

        return arrayMyRencentlyViewed;
    }
}