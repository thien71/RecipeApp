package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.adapter.MyRatedAdapter;
import com.example.recipe_app.model.MyRated;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyRatedActivity extends AppCompatActivity {
    List<MyRated> danhGiaList;
    private RecyclerView rcvMyRated;
    private MyRatedAdapter myRatedAdapter;
    private ImageButton ibtnBack;
    private int maNguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rated);

        rcvMyRated = (RecyclerView) findViewById(R.id.rcvMyRated);
        ibtnBack = (ImageButton) findViewById(R.id.ibtnBackMyRated);

        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        maNguoiDung = intent.getIntExtra("maNguoiDung", 1);

        danhGiaList = new ArrayList<>();
        fetchDanhGiaList();
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
    private void setupRecyclerViewMyRated() {
        LinearLayoutManager danhGiaLayoutManager = new LinearLayoutManager(MyRatedActivity.this);
        rcvMyRated.setLayoutManager(danhGiaLayoutManager);
        rcvMyRated.setFocusable(false);
        rcvMyRated.setNestedScrollingEnabled(false);
        myRatedAdapter = new MyRatedAdapter(danhGiaList);
        rcvMyRated.setAdapter(myRatedAdapter);
    }
}