package com.example.recipe_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.recipe_app.adapter.CommunityAdapter;
import com.example.recipe_app.model.BaiDangCongDong;
import com.example.recipe_app.model.NguoiDung;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommunityFragment extends Fragment {
    RecyclerView rcvCommunity;
    CommunityAdapter communityAdapter;
    private int maNguoiDung;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static CommunityFragment newInstance(int maNguoiDung) {
        CommunityFragment fragment = new CommunityFragment();
        Bundle args = new Bundle();
        args.putInt("maNguoiDung", maNguoiDung);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);

        rcvCommunity = view.findViewById(R.id.rcvCommunity);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshCommunity);

        new LoadCommunityDataTask().execute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new LoadCommunityDataTask().execute();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    private class LoadCommunityDataTask extends AsyncTask<Void, Void, List<BaiDangCongDong>> {

        @Override
        protected List<BaiDangCongDong> doInBackground(Void... voids) {
            List<BaiDangCongDong> baiDangCongDongList = new ArrayList<>();

            DatabaseReference baiDangRef = FirebaseDatabase.getInstance().getReference("BaiDangCongDong");
            DatabaseReference nguoiDungRef = FirebaseDatabase.getInstance().getReference("NguoiDung");

            baiDangRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot baiDangSnapshot : dataSnapshot.getChildren()) {
                        int maNguoiDung = baiDangSnapshot.child("maNguoiDung").getValue(Integer.class);

                        nguoiDungRef.child(String.valueOf(maNguoiDung)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot nguoiDungSnapshot) {
                                String tenNguoiDung = nguoiDungSnapshot.child("tenNguoiDung").getValue(String.class);
                                String linkAvatar = nguoiDungSnapshot.child("avatar").getValue(String.class);

                                NguoiDung nguoiDung = new NguoiDung(tenNguoiDung, linkAvatar);

                                String hinhAnh = baiDangSnapshot.child("hinhAnh").getValue(String.class);
                                int maBaiDang = Integer.parseInt(baiDangSnapshot.getKey());
                                String noiDung = baiDangSnapshot.child("noiDung").getValue(String.class);
                                int soBinhLuan = baiDangSnapshot.child("soBinhLuan").getValue(Integer.class);
                                int soLike = baiDangSnapshot.child("soLike").getValue(Integer.class);
                                String tieuDe = baiDangSnapshot.child("tieuDe").getValue(String.class);

                                BaiDangCongDong baiDang = new BaiDangCongDong(maNguoiDung, maBaiDang, tieuDe, noiDung, hinhAnh, soLike, soBinhLuan, nguoiDung);
                                baiDangCongDongList.add(baiDang);

                                setupRecyclerView(baiDangCongDongList);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });

            return baiDangCongDongList;
        }
    }
    private void setupRecyclerView(List<BaiDangCongDong> baiDangCongDongList) {
        LinearLayoutManager communityLayoutManager = new LinearLayoutManager(getActivity());
        rcvCommunity.setLayoutManager(communityLayoutManager);

        communityAdapter = new CommunityAdapter(baiDangCongDongList);
        rcvCommunity.setAdapter(communityAdapter);

        communityAdapter.setOnLikeButtonClickListener(new CommunityAdapter.OnLikeButtonClickListener() {
            @Override
            public void onLikeButtonClick(int position) {
                BaiDangCongDong baiDang = baiDangCongDongList.get(position);
                baiDang.setLiked(!baiDang.isLiked());

                int likeCount = baiDang.getSoLike() + (baiDang.isLiked() ? 1 : -1);
                baiDang.setSoLike(likeCount);
                communityAdapter.notifyItemChanged(position);
            }
        });

        setRecyclerViewItemClickListener(baiDangCongDongList);
    }
    private void setRecyclerViewItemClickListener(List<BaiDangCongDong> dataList) {
        if (communityAdapter != null) {
            communityAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (getArguments() != null) {
                        maNguoiDung = getArguments().getInt("maNguoiDung", 1);
                    }

                    BaiDangCongDong baiDangItem = dataList.get(position);
                    Intent intent = new Intent(getActivity(), DetailCommunityActivity.class);
                    intent.putExtra("baiDangItem", baiDangItem);
                    intent.putExtra("maBaiDang", position);
                    intent.putExtra("maNguoiDung", maNguoiDung);
                    startActivity(intent);
                }
            });
        }
    }
}