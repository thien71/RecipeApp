package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.adapter.BuocThucHienAdapter;
import com.example.recipe_app.model.BaiDangCongDong;
import com.example.recipe_app.model.CongThuc;
import com.example.recipe_app.model.NguoiDung;
import com.example.recipe_app.model.NguyenLieu;
import com.example.recipe_app.model.UaThich;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SavedFragment extends Fragment {
    ArrayList<CongThuc> uaThichList;
    SavedAdapter uaThichAdapter;
    RecyclerView rcvUaThich;
    DatabaseReference uaThichRef, congThucRef;
    private LinearLayout search_bar_Saved;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved, container, false);

        rcvUaThich = (RecyclerView) view.findViewById(R.id.rcvUaThich);
        search_bar_Saved = (LinearLayout) view.findViewById(R.id.search_bar_Saved);

        uaThichRef = FirebaseDatabase.getInstance().getReference("UaThich");
        uaThichRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                uaThichList = new ArrayList<>();
                int totalItems = (int) dataSnapshot.getChildrenCount();
                AtomicInteger itemCount = new AtomicInteger();

                for (DataSnapshot uaThichSnapshot : dataSnapshot.getChildren()) {
                    int maCongThuc = uaThichSnapshot.child("maCongThuc").getValue(Integer.class);

                    congThucRef = FirebaseDatabase.getInstance().getReference("CongThuc").child(String.valueOf(maCongThuc));
                    congThucRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot congThucSnapshot) {
                            int maCongThuc = congThucSnapshot.child("maCongThuc").getValue(Integer.class);
                            String tieuDe = congThucSnapshot.child("tieuDe").getValue(String.class);
                            String duongDanHinhAnh = congThucSnapshot.child("duongDanHinhAnh").getValue(String.class);

                            CongThuc congThuc = new CongThuc(maCongThuc, tieuDe, duongDanHinhAnh);
                            uaThichList.add(congThuc);

                            if (itemCount.incrementAndGet() == totalItems) {
                                setupRecyclerView();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

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

        uaThichAdapter = new SavedAdapter(uaThichList);
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