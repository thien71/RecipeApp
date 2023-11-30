package com.example.recipe_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.adapter.BuocThucHienAdapter;
import com.example.recipe_app.adapter.NguyenLieuAdapter;
import com.example.recipe_app.model.BaiDangCongDong;
import com.example.recipe_app.model.NguoiDung;
import com.example.recipe_app.model.NguyenLieu;
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
    List<BaiDangCongDong> baiDangCongDongList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);

        rcvCommunity = view.findViewById(R.id.rcvCommunity);

        DatabaseReference baiDangRef = FirebaseDatabase.getInstance().getReference("BaiDangCongDong");
        DatabaseReference nguoiDungRef = FirebaseDatabase.getInstance().getReference("NguoiDung");
        baiDangRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                baiDangCongDongList = new ArrayList<>();

                for (DataSnapshot baiDangSnapshot : dataSnapshot.getChildren()) {
                    int maNguoiDung = baiDangSnapshot.child("maNguoiDung").getValue(Integer.class);
                    // Đọc thông tin bài đăng từ node BaiDangCongDong

                    nguoiDungRef.child(String.valueOf(maNguoiDung)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot nguoiDungSnapshot) {
                            // Lấy thông tin về người dùng từ node NguoiDung
                            String tenNguoiDung = nguoiDungSnapshot.child("tenNguoiDung").getValue(String.class);
                            String linkAvatar = nguoiDungSnapshot.child("avatar").getValue(String.class);

                            // Tạo đối tượng NguoiDung
                            NguoiDung nguoiDung = new NguoiDung(tenNguoiDung, linkAvatar);

                            // Lấy thông tin từ bài đăng
                            String hinhAnh = baiDangSnapshot.child("hinhAnh").getValue(String.class);
                            int maBaiDang = Integer.parseInt(baiDangSnapshot.getKey());
                            String ngayDang = baiDangSnapshot.child("ngayDang").getValue(String.class);
                            String noiDung = baiDangSnapshot.child("noiDung").getValue(String.class);
                            int soBinhLuan = baiDangSnapshot.child("soBinhLuan").getValue(Integer.class);
                            int soLike = baiDangSnapshot.child("soLike").getValue(Integer.class);
                            String tieuDe = baiDangSnapshot.child("tieuDe").getValue(String.class);

                            // Tạo đối tượng BaiDangCongDong với thông tin người dùng
                            BaiDangCongDong baiDang = new BaiDangCongDong(maNguoiDung, maBaiDang, tieuDe, noiDung, hinhAnh, soLike, soBinhLuan, nguoiDung);
                            baiDangCongDongList.add(baiDang);

                            if (baiDangCongDongList.size() == dataSnapshot.getChildrenCount()) {
                                // Hiển thị danh sách
                                LinearLayoutManager communityLayoutManager = new LinearLayoutManager(getActivity());
                                rcvCommunity.setLayoutManager(communityLayoutManager);
                                communityAdapter = new CommunityAdapter(baiDangCongDongList);
                                rcvCommunity.setAdapter(communityAdapter);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Xử lý lỗi nếu có
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi nếu có
            }
        });
        return view;
    }
}