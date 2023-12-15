package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.adapter.UaThichAdapter;
import com.example.recipe_app.model.CongThuc;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SavedFragment extends Fragment {
    ArrayList<CongThuc> uaThichList;
    UaThichAdapter uaThichAdapter;
    RecyclerView rcvUaThich;
    private LinearLayout search_bar_Saved;

    private int maNguoiDung;
    public static SavedFragment newInstance(int maNguoiDung) {
        SavedFragment fragment = new SavedFragment();
        Bundle args = new Bundle();
        args.putInt("maNguoiDung", maNguoiDung);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved, container, false);

        rcvUaThich = (RecyclerView) view.findViewById(R.id.rcvUaThich);
        search_bar_Saved = (LinearLayout) view.findViewById(R.id.search_bar_Saved);

        if (getArguments() != null) {
            maNguoiDung = getArguments().getInt("maNguoiDung", 1);
        }

        DatabaseReference nguoiDungRef = FirebaseDatabase.getInstance().getReference("NguoiDung").child(String.valueOf(maNguoiDung)).child("UaThich");
        nguoiDungRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uaThichList = new ArrayList<>();

                for (DataSnapshot uaThichSnapshot : dataSnapshot.getChildren()) {
                    int maCongThuc = uaThichSnapshot.child("maCongThuc").getValue(Integer.class);

                    DatabaseReference congThucRef = FirebaseDatabase.getInstance().getReference("CongThuc").child(String.valueOf(maCongThuc));
                    congThucRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String tieuDe = dataSnapshot.child("tieuDe").getValue(String.class);
                            String duongDanHinhAnh = dataSnapshot.child("duongDanHinhAnh").getValue(String.class);

                            CongThuc congThuc = new CongThuc(maCongThuc, tieuDe, duongDanHinhAnh);
                            uaThichList.add(congThuc);

                            setupRecyclerView();
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

        // search
        search_bar_Saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
    private void setupRecyclerView() {
        GridLayoutManager uaThichLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcvUaThich.setLayoutManager(uaThichLayoutManager);

        uaThichAdapter = new UaThichAdapter(uaThichList);
        rcvUaThich.setFocusable(false);
        rcvUaThich.setNestedScrollingEnabled(false);
        rcvUaThich.setAdapter(uaThichAdapter);

        uaThichAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("maCongThuc", uaThichList.get(position).getMaCongThuc());
                startActivity(intent);
            }
        });
    }
}