package com.example.recipe_app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.adapter.MyRatedAdapter;
import com.example.recipe_app.adapter.MyTipsAdapter;
import com.example.recipe_app.model.MyRated;
import com.example.recipe_app.model.MyTips;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityFragment extends Fragment {

    private RecyclerView rcvMyRated;
    private RecyclerView rcvMyTips;
//    private RecyclerView rcvReccentlyViewed;
    List<MyRated> danhGiaList;
    List<MyTips> binhLuanList;

    private MyRatedAdapter myRatedAdapter;
    private MyTipsAdapter myTipsAdapter;
//    private MyRecentlyViewedAdapter myRecentlyViewedAdapter;

    private RecyclerViewItemClickListener itemClickListener;
    public void setOnItemClickListener(RecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }
    private int maNguoiDung;

    public ActivityFragment() {

    }

    public static ActivityFragment newInstance(int maNguoiDung) {
        ActivityFragment fragment = new ActivityFragment();
        Bundle args = new Bundle();
        args.putInt("maNguoiDung", maNguoiDung);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        //rcvReccentlyViewed = (RecyclerView) view.findViewById(R.id.rcvRecentlyViewed);

        if (getArguments() != null) {
            maNguoiDung = getArguments().getInt("maNguoiDung", 1);
        }

        rcvMyRated = (RecyclerView) view.findViewById(R.id.rcvMyRatedProfileActivity);
        rcvMyTips = (RecyclerView) view.findViewById(R.id.rcvMyTipsProfileActivity);
        danhGiaList = new ArrayList<>();
        binhLuanList = new ArrayList<>();

        fetchDanhGiaList();

        fetchBinhLuanList();

        return view;
    }
    private void fetchDanhGiaList() {
        DatabaseReference nguoiDungRef = FirebaseDatabase.getInstance().getReference("NguoiDung").child(String.valueOf(maNguoiDung)).child("Like");

        nguoiDungRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    final List<Task<DataSnapshot>> tasks = new ArrayList<>();

                    for (DataSnapshot likeSnapshot : snapshot.getChildren()) {
                        Object rawValue = likeSnapshot.getValue();

                        if (rawValue != null) {
                            try {
                                int maBaiDang = Integer.parseInt(String.valueOf(rawValue));
                                DatabaseReference baiDangRef = FirebaseDatabase.getInstance().getReference("BaiDangCongDong").child(String.valueOf(maBaiDang));
                                Task<DataSnapshot> task = baiDangRef.get();
                                tasks.add(task);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    Task<Void> allTasks = Tasks.whenAll(tasks);
                    allTasks.addOnSuccessListener(aVoid -> {
                        for (Task<DataSnapshot> task : tasks) {
                            DataSnapshot baiDangSnapshot = task.getResult();
                            if (baiDangSnapshot != null && baiDangSnapshot.exists()) {
                                String hinhAnh = baiDangSnapshot.child("hinhAnh").getValue(String.class);
                                String tieuDe = baiDangSnapshot.child("tieuDe").getValue(String.class);
                                danhGiaList.add(new MyRated(hinhAnh, tieuDe));
                            }
                        }
                        setupRecyclerViewMyRated();
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    private void fetchBinhLuanList() {
        DatabaseReference nguoiDungRef = FirebaseDatabase.getInstance().getReference("NguoiDung").child(String.valueOf(maNguoiDung)).child("BinhLuan");

        nguoiDungRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot binhLuanSnapshot : snapshot.getChildren()) {
                    String noiDung = binhLuanSnapshot.child("noiDungBinhLuan").getValue(String.class);
                    String thoiGian = binhLuanSnapshot.child("ngayTao").getValue(String.class);

                    Object rawValue = binhLuanSnapshot.child("maBaiDang").getValue();
                    if (rawValue != null) {
                        try {
                            int maBaiDang = Integer.parseInt(String.valueOf(rawValue));
                            DatabaseReference baiDangRef = FirebaseDatabase.getInstance().getReference("BaiDangCongDong").child(String.valueOf(maBaiDang));

                            baiDangRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot baiDangSnapshot) {
                                    if (baiDangSnapshot.exists()) {
                                        String tieuDe = baiDangSnapshot.child("tieuDe").getValue(String.class);
                                        binhLuanList.add(new MyTips(noiDung, tieuDe, thoiGian));
                                        setupRecyclerViewMyTips();
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
    private void setupRecyclerViewMyRated() {
        LinearLayoutManager danhGiaLayoutManager = new LinearLayoutManager(getActivity());
        rcvMyRated.setLayoutManager(danhGiaLayoutManager);
        rcvMyRated.setFocusable(false);
        rcvMyRated.setNestedScrollingEnabled(false);
        myRatedAdapter = new MyRatedAdapter(danhGiaList);
        rcvMyRated.setAdapter(myRatedAdapter);
    }

    private void setupRecyclerViewMyTips() {
        LinearLayoutManager binhLuanLayoutManager = new LinearLayoutManager(getActivity());
        rcvMyTips.setLayoutManager(binhLuanLayoutManager);
        rcvMyTips.setFocusable(false);
        rcvMyTips.setNestedScrollingEnabled(false);
        myTipsAdapter = new MyTipsAdapter(binhLuanList);
        rcvMyTips.setAdapter(myTipsAdapter);
    }
}