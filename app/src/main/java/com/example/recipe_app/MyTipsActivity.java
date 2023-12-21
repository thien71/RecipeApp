package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.adapter.MyTipsAdapter;
import com.example.recipe_app.model.MyTips;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyTipsActivity extends AppCompatActivity {
    private RecyclerView rcvMyTips;
    private MyTipsAdapter myTipsAdapter;
    private ImageButton ibtnBack;
    List<MyTips> binhLuanList;
    private int maNguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tips);

        rcvMyTips = (RecyclerView) findViewById(R.id.rcvMyTips);
        ibtnBack = (ImageButton) findViewById(R.id.ibtnBackMyTips);

        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        maNguoiDung = intent.getIntExtra("maNguoiDung", 1);

        binhLuanList = new ArrayList<>();
        fetchBinhLuanList();

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

    private void setupRecyclerViewMyTips() {
        LinearLayoutManager binhLuanLayoutManager = new LinearLayoutManager(MyTipsActivity.this);
        rcvMyTips.setLayoutManager(binhLuanLayoutManager);
        rcvMyTips.setFocusable(false);
        rcvMyTips.setNestedScrollingEnabled(false);
        myTipsAdapter = new MyTipsAdapter(binhLuanList);
        rcvMyTips.setAdapter(myTipsAdapter);
    }
}