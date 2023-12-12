package com.example.recipe_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipe_app.adapter.BinhLuanAdapter;
import com.example.recipe_app.adapter.CommunityAdapter;
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

import dalvik.annotation.optimization.CriticalNative;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailCommunityActivity extends AppCompatActivity {
    TextView txtTenNguoiDung, txtTenTieuDe, txtNoiDung, txtSoLike, txtSoBinhLuan, txtTenMon;
    CircleImageView cirAvatar;
    ImageView imgHinh;
    ImageButton ibtnBack;
    RecyclerView rcvBinhLuan;
    List<BinhLuan> binhLuanList;
    BinhLuanAdapter binhLuanAdapter;
    private int maBaiDang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_community);

        Mapping();

        Intent intent = getIntent();
        if (intent != null) {
            maBaiDang = intent.getIntExtra("maBaiDang", 1) + 1;
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
                            Log.d("THANH THIEN 4", tenNguoiDung);
                            String avatar = nguoiDungSnapshot.child("avatar").getValue(String.class);
                            Log.d("THANH THIEN 5", avatar);
                            String noiDungBinhLuan = binhLuanItem.child("noiDungBinhLuan").getValue(String.class);
                            Log.d("THANH THIEN 6", noiDungBinhLuan);

                            BinhLuan binhLuan = new BinhLuan(tenNguoiDung, avatar, noiDungBinhLuan);
                            binhLuanList.add(binhLuan);
                        }
                    }
                }
                setupRecyclerView(binhLuanList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupRecyclerView(List<BinhLuan> binhLuanList) {
        LinearLayoutManager communityLayoutManager = new LinearLayoutManager(DetailCommunityActivity.this);
        rcvBinhLuan.setLayoutManager(communityLayoutManager);

        binhLuanAdapter = new BinhLuanAdapter(binhLuanList);
        rcvBinhLuan.setAdapter(binhLuanAdapter);

    }
    private void Mapping() {
        txtTenNguoiDung = (TextView) findViewById(R.id.txtTenNguoiDungDetailCommunity);
        txtTenMon = (TextView) findViewById(R.id.txtTenMonDetailCommunity);
        txtNoiDung = (TextView) findViewById(R.id.txtNoiDungDetailCommunity);
        txtSoLike = (TextView) findViewById(R.id.txtSoLikeDetailCommunity);
        txtSoBinhLuan = (TextView) findViewById(R.id.txtSoBinhLuanDetailCommunity);

        txtTenTieuDe = (TextView) findViewById(R.id.txtTenTieuDeDetailCommunity);

        imgHinh = (ImageView) findViewById(R.id.imgHinhDetailCommunity);
        cirAvatar = (CircleImageView) findViewById(R.id.civAvatarDetailCommunity);

        ibtnBack = (ImageButton) findViewById(R.id.ibtnBackDetailCommunity);

        rcvBinhLuan = (RecyclerView) findViewById(R.id.rcvBinhLuanCommunity);
    }
}