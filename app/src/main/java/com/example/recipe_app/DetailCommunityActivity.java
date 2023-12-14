package com.example.recipe_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipe_app.adapter.BinhLuanAdapter;
import com.example.recipe_app.model.BaiDangCongDong;
import com.example.recipe_app.model.BinhLuan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailCommunityActivity extends AppCompatActivity {
    TextView txtTenNguoiDung, txtTenTieuDe, txtNoiDung, txtSoLike, txtSoBinhLuan, txtTenMon;
    EditText edtBinhLuan;
    CircleImageView cirAvatar;
    ImageView imgHinh;
    ImageButton ibtnBack, ibtnGuiBinhLuan;
    RecyclerView rcvBinhLuan;
    List<BinhLuan> binhLuanList;
    BinhLuanAdapter binhLuanAdapter;
    private int maBaiDang;
    private int maNguoiDung;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_community);

        Mapping();
        init();

        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ibtnGuiBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noiDungBinhLuan = edtBinhLuan.getText().toString().trim();
                addNewComment(noiDungBinhLuan);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadComments();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setupRecyclerView() {
        LinearLayoutManager communityLayoutManager = new LinearLayoutManager(this);
        rcvBinhLuan.setLayoutManager(communityLayoutManager);

        binhLuanAdapter = new BinhLuanAdapter(binhLuanList);
        rcvBinhLuan.setAdapter(binhLuanAdapter);
    }

    private void loadComments() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NguoiDung");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binhLuanList = new ArrayList<>();
                for (DataSnapshot nguoiDungSnapshot : snapshot.getChildren()) {
                    DataSnapshot binhLuanSnapshot = nguoiDungSnapshot.child("BinhLuan");
                    for (DataSnapshot binhLuanItem : binhLuanSnapshot.getChildren()) {
                        int maBaiDangTrongNode = binhLuanItem.child("maBaiDang").getValue(Integer.class);
                        if (maBaiDangTrongNode == maBaiDang) {
                            String tenNguoiDung = nguoiDungSnapshot.child("tenNguoiDung").getValue(String.class);
                            String avatar = nguoiDungSnapshot.child("avatar").getValue(String.class);
                            String noiDungBinhLuan = binhLuanItem.child("noiDungBinhLuan").getValue(String.class);

                            BinhLuan binhLuan = new BinhLuan(tenNguoiDung, avatar, noiDungBinhLuan);
                            binhLuanList.add(binhLuan);
                        }
                    }
                }
                setupRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addNewComment(String noiDungBinhLuan) {
        if (!noiDungBinhLuan.isEmpty()) {
            DatabaseReference nguoiDungRef = FirebaseDatabase.getInstance().getReference("NguoiDung").child(String.valueOf(maNguoiDung));
            nguoiDungRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String tenNguoiDung = dataSnapshot.child("tenNguoiDung").getValue(String.class);
                        String avatar = dataSnapshot.child("avatar").getValue(String.class);

                        long soLuongBinhLuan = dataSnapshot.child("BinhLuan").getChildrenCount();
                        int maBinhLuan = (int) (soLuongBinhLuan + 1);

                        BinhLuan binhLuanMoi = new BinhLuan(maBaiDang, maBinhLuan, noiDungBinhLuan);

                        DatabaseReference binhLuanMoiRef = nguoiDungRef.child("BinhLuan").child(String.valueOf(maBinhLuan-1));
                        binhLuanMoiRef.setValue(binhLuanMoi);

                        DatabaseReference baiDangRef = FirebaseDatabase.getInstance().getReference("BaiDangCongDong").child(String.valueOf(maBaiDang));
                        baiDangRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    long soBinhLuan = (long) dataSnapshot.child("soBinhLuan").getValue();

                                    baiDangRef.child("soBinhLuan").setValue(soBinhLuan + 1);

                                    BinhLuan binhLuan = new BinhLuan(tenNguoiDung, avatar, noiDungBinhLuan);
                                    binhLuanList.add(binhLuan);
                                    binhLuanAdapter.notifyItemInserted(binhLuanList.size() - 1);

                                    loadComments();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        edtBinhLuan.setText("");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void init() {
        binhLuanList = new ArrayList<>();
        setupRecyclerView();

        Intent intent = getIntent();
        if (intent != null) {
            maBaiDang = intent.getIntExtra("maBaiDang", 1) + 1;
            maNguoiDung = intent.getIntExtra("maNguoiDung", 1);
            BaiDangCongDong baiDangItem = (BaiDangCongDong) intent.getSerializableExtra("baiDangItem");

            txtTenTieuDe.setText("Bài viết của " + baiDangItem.getNguoiDung().getTenNguoiDung());
            txtTenMon.setText(baiDangItem.getTieuDe());
            txtTenNguoiDung.setText(baiDangItem.getNguoiDung().getTenNguoiDung());
            txtNoiDung.setText(baiDangItem.getNoiDung());
            txtSoLike.setText(baiDangItem.getSoLike() + "");
            txtSoBinhLuan.setText(baiDangItem.getSoBinhLuan()+"");

            Picasso.get().load(baiDangItem.getHinhAnh()).into(imgHinh);
            Picasso.get().load(baiDangItem.getNguoiDung().getAvatar()).into(cirAvatar);
        }

        loadComments();
    }

    private void Mapping() {
        txtTenNguoiDung = findViewById(R.id.txtTenNguoiDungDetailCommunity);
        txtTenMon = findViewById(R.id.txtTenMonDetailCommunity);
        txtNoiDung = findViewById(R.id.txtNoiDungDetailCommunity);
        txtSoLike = findViewById(R.id.txtSoLikeDetailCommunity);
        txtSoBinhLuan = findViewById(R.id.txtSoBinhLuanDetailCommunity);

        txtTenTieuDe = findViewById(R.id.txtTenTieuDeDetailCommunity);

        imgHinh = findViewById(R.id.imgHinhDetailCommunity);
        cirAvatar = findViewById(R.id.civAvatarDetailCommunity);

        ibtnBack = findViewById(R.id.ibtnBackDetailCommunity);

        rcvBinhLuan = findViewById(R.id.rcvBinhLuanCommunity);

        edtBinhLuan = findViewById(R.id.edtBinhLuan);
        ibtnGuiBinhLuan = findViewById(R.id.ibtnGuiBinhLuan);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshDetailCommunity);
    }
}
