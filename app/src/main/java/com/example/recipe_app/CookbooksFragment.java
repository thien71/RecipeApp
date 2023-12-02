package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.adapter.CookbooksAdapter;
import com.example.recipe_app.model.Cookbooks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CookbooksFragment extends Fragment {
    RecyclerView rcvDanhMuc;
    ArrayList<Cookbooks> danhMucDaLuuList;
    CookbooksAdapter danhMucDaLuuAdapter;
    DatabaseReference danhMucRef, congThucRef , uaThichRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cookbooks, container, false);

        rcvDanhMuc = (RecyclerView) view.findViewById(R.id.rcvDanhMuc);
        danhMucRef = FirebaseDatabase.getInstance().getReference("DanhMucCongThuc");
        uaThichRef = FirebaseDatabase.getInstance().getReference("UaThich");

        danhMucDaLuuList = new ArrayList<>();
        uaThichRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Map<Integer, Integer> countMap = new HashMap<>();
                for (DataSnapshot uaThichSnapshot : dataSnapshot.getChildren()) {
                    int maCongThuc = uaThichSnapshot.child("maCongThuc").getValue(Integer.class);

                    congThucRef = FirebaseDatabase.getInstance().getReference("CongThuc").child(String.valueOf(maCongThuc));
                    congThucRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot congThucSnapshot) {
                            int maDanhMucCongThuc = congThucSnapshot.child("maDanhMucCongThuc").getValue(Integer.class);

                            int count = countMap.containsKey(maDanhMucCongThuc) ? countMap.get(maDanhMucCongThuc) + 1 : 1;
                            countMap.put(maDanhMucCongThuc, count);
                            danhMucRef.child(String.valueOf(maDanhMucCongThuc)).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot danhMucSnapshot) {
                                    String tenDanhMuc = danhMucSnapshot.child("tenDanhMuc").getValue(String.class);
                                    String duongDanHinhAnh = danhMucSnapshot.child("duongDanHinhAnh").getValue(String.class);

                                    int countForThisDanhMuc = countMap.get(maDanhMucCongThuc);

                                    Cookbooks cookbooks = new Cookbooks(duongDanHinhAnh, tenDanhMuc, countForThisDanhMuc, maCongThuc);
                                    danhMucDaLuuList.add(cookbooks);

                                    setupRecyclerView();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
    private void setupRecyclerView() {
        LinearLayoutManager danhMucLayoutManager = new LinearLayoutManager(getActivity());
        rcvDanhMuc.setLayoutManager(danhMucLayoutManager);

        danhMucDaLuuAdapter = new CookbooksAdapter(danhMucDaLuuList);
        rcvDanhMuc.setAdapter(danhMucDaLuuAdapter);

        danhMucDaLuuAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("maCongThuc", danhMucDaLuuList.get(position).getMaCongThuc());
                startActivity(intent);
            }
        });
    }
}