package com.example.recipe_app;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.adapter.BuocThucHienAdapter;
import com.example.recipe_app.adapter.NguyenLieuAdapter;
import com.example.recipe_app.model.BuocThucHien;
import com.example.recipe_app.model.NguyenLieu;
import com.example.recipe_app.model.ThongTinDinhDuong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    ImageView imgHinh;
    ImageButton ibtnBackDetail, ibtnTymDetail, ibtnAnHienThongTin, ibtnGiamSoLuong, ibtnTangSoLuong;
    TextView txtTenMonTieuDem, txtTenMon, txtAnHienThongTin, txtSoPhanAnDetail, txtMoTa;
    TextView txtCalo, txtProtein, txtChatBeo, txtTinhBot;
    EditText edtSoPhanAnDetail;
    RecyclerView rcvNguyenLieu, rcvBuocTienHanh;
    WebView webView;
    LinearLayout linearAnHienThongTinDinhDuong, listThongTinDinhDuong;

    List<NguyenLieu> nguyenLieuList;

    NguyenLieuAdapter nguyenLieuAdapter;

    int calo, protein, carbohydrate, chatBeo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Mapping();

        int maCongThuc = getIntent().getIntExtra("maCongThuc", 1);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("CongThuc").child(String.valueOf(maCongThuc));

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String tieuDe = dataSnapshot.child("tieuDe").getValue(String.class);
                    String moTa = dataSnapshot.child("moTa").getValue(String.class);
                    String linkVideo = dataSnapshot.child("video").child("duongDanVideo").getValue(String.class);

                    //get Nguyenlieu
                    nguyenLieuList = new ArrayList<>();
                    for (DataSnapshot nguyenLieuSnapshot : dataSnapshot.child("nguyenLieu").getChildren()) {
                        String tenNguyenLieu = nguyenLieuSnapshot.child("tenNguyenLieu").getValue(String.class);
                        int soLuong = nguyenLieuSnapshot.child("soLuong").getValue(Integer.class);
                        String donVi = nguyenLieuSnapshot.child("donVi").getValue(String.class);

                        NguyenLieu nguyenLieu = new NguyenLieu(tenNguyenLieu, soLuong, donVi);
                        nguyenLieuList.add(nguyenLieu);
                    }
                    // Thông tin dinh dưỡng
                    DataSnapshot thongTinDinhDuongSnapshot = dataSnapshot.child("thongTinDinhDuong");
                    calo = thongTinDinhDuongSnapshot.child("calo").getValue(Integer.class);
                    carbohydrate = thongTinDinhDuongSnapshot.child("carbohydrate").getValue(Integer.class);
                    chatBeo = thongTinDinhDuongSnapshot.child("chatBeo").getValue(Integer.class);
                    protein = thongTinDinhDuongSnapshot.child("protein").getValue(Integer.class);

                    // Bước thực hiện
                    List<BuocThucHien> buocThucHienList = new ArrayList<>();
                    for (DataSnapshot buocTienHanhSnapshot : dataSnapshot.child("buocThucHien").getChildren()) {
                            int maBuoc = buocTienHanhSnapshot.child("maBuoc").getValue(Integer.class);
                            String moTaBuoc = buocTienHanhSnapshot.child("moTa").getValue(String.class);

                            BuocThucHien buocThucHien = new BuocThucHien(maBuoc, moTaBuoc);
                            buocThucHienList.add(buocThucHien);
                    }
                    // Sau khi lấy dữ liệu từ Firebase, bạn có thể hiển thị nó trên giao diện của DetailActivity.
                    txtTenMon.setText(tieuDe);
                    txtTenMonTieuDem.setText(tieuDe);
                    txtMoTa.setText(moTa);

                    webView.loadData(linkVideo, "text/html", "utf-8");
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.setWebChromeClient(new WebChromeClient());

                    // Hiển thị Nguyên liệu
                    LinearLayoutManager nguyenLieuLayoutManager = new LinearLayoutManager(DetailActivity.this);
                    rcvNguyenLieu.setLayoutManager(nguyenLieuLayoutManager);
                    nguyenLieuAdapter = new NguyenLieuAdapter(nguyenLieuList);
                    rcvNguyenLieu.setAdapter(nguyenLieuAdapter);

                    // Thông tin dinh dưỡng
                    txtCalo.setText(calo + "");
                    txtProtein.setText(protein + "g");
                    txtChatBeo.setText(chatBeo + "g");
                    txtTinhBot.setText(carbohydrate + "g");

                    // Hiển thị Bước thực hiện
                    LinearLayoutManager buocThucHienLayoutManager = new LinearLayoutManager(DetailActivity.this);
                    rcvBuocTienHanh.setLayoutManager(buocThucHienLayoutManager);
                    BuocThucHienAdapter buocThucHienAdapter = new BuocThucHienAdapter(buocThucHienList);
                    rcvBuocTienHanh.setAdapter(buocThucHienAdapter);

                } else {

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        ibtnBackDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ibtnTymDetail.setOnClickListener(new View.OnClickListener() {
            boolean isTym = false;
            @Override
            public void onClick(View v) {
                if(isTym) {
                    ibtnTymDetail.setImageResource(R.drawable.baseline_favorite_border_24);
                } else {
                    ibtnTymDetail.setImageResource(R.drawable.baseline_favorite_24);
                }
                isTym = !isTym;
            }
        });
        ibtnGiamSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soPhanAn = Integer.parseInt(String.valueOf(edtSoPhanAnDetail.getText()));
                if (soPhanAn > 1) {
                    soPhanAn -= 1;

                    for (NguyenLieu nguyenLieu : nguyenLieuList) {
                        int soLuongGoc = nguyenLieu.getSoLuongGoc();
                        int soLuongMoi = soLuongGoc * soPhanAn;
                        nguyenLieu.setSoLuong(soLuongMoi);
                    }
                    nguyenLieuAdapter.notifyDataSetChanged();

                    txtCalo.setText(calo * soPhanAn + "");
                    txtProtein.setText(protein * soPhanAn + "g");
                    txtChatBeo.setText(chatBeo * soPhanAn + "g");
                    txtTinhBot.setText(carbohydrate * soPhanAn + "g");

                    edtSoPhanAnDetail.setText(soPhanAn + "");
                    txtSoPhanAnDetail.setText(soPhanAn + " phần ăn");
                }
            }
        });
        ibtnTangSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soPhanAn = Integer.parseInt(String.valueOf(edtSoPhanAnDetail.getText()));
                soPhanAn += 1;

                for (NguyenLieu nguyenLieu : nguyenLieuList) {
                    int soLuongGoc = nguyenLieu.getSoLuongGoc();
                    int soLuongMoi = soLuongGoc * soPhanAn;
                    nguyenLieu.setSoLuong(soLuongMoi);
                }
                nguyenLieuAdapter.notifyDataSetChanged();

                txtCalo.setText(calo * soPhanAn + "");
                txtProtein.setText(protein * soPhanAn + "g");
                txtChatBeo.setText(chatBeo * soPhanAn + "g");
                txtTinhBot.setText(carbohydrate * soPhanAn + "g");

                edtSoPhanAnDetail.setText(soPhanAn + "");
                txtSoPhanAnDetail.setText(soPhanAn + " phần ăn");
            }
        });
        edtSoPhanAnDetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int soPhanAn = Integer.parseInt(s.toString());

                    for (NguyenLieu nguyenLieu : nguyenLieuList) {
                        int soLuongGoc = nguyenLieu.getSoLuongGoc();
                        int soLuongMoi = soLuongGoc * soPhanAn;
                        nguyenLieu.setSoLuong(soLuongMoi);
                    }
                    nguyenLieuAdapter.notifyDataSetChanged();
                    txtSoPhanAnDetail.setText(soPhanAn + " phần ăn");
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        // Ẩn hiện thông tin dinh dưỡng
        linearAnHienThongTinDinhDuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int listThongTinDinhDuongVisibility = listThongTinDinhDuong.getVisibility();

                if (listThongTinDinhDuongVisibility == View.VISIBLE) {
                    txtAnHienThongTin.setText("Hiện");
                    ibtnAnHienThongTin.setImageResource(R.drawable.baseline_add_24);
                    listThongTinDinhDuong.setVisibility(View.GONE);
                } else {
                    txtAnHienThongTin.setText("Ẩn");
                    listThongTinDinhDuong.setVisibility(View.VISIBLE);
                    ibtnAnHienThongTin.setImageResource(R.drawable.baseline_remove_24);
                }
            }
        });
    }

    private void Mapping() {
        //imgHinh = (ImageView) findViewById(R.id.imgHinhDetail);
        txtTenMon = (TextView) findViewById(R.id.txtTenMonDetail);
        txtTenMonTieuDem = (TextView) findViewById(R.id.txtTenMonDetailTopic);
        txtMoTa = (TextView) findViewById(R.id.txtMoTaDetail);
        webView = (WebView) findViewById(R.id.webView);

        rcvNguyenLieu = (RecyclerView) findViewById(R.id.rcvNguyenLieu);
        rcvBuocTienHanh = (RecyclerView) findViewById(R.id.rcvBuocTienHanh);

        txtCalo = (TextView) findViewById(R.id.txtCalo);
        txtProtein = (TextView) findViewById(R.id.txtProtein);
        txtChatBeo = (TextView) findViewById(R.id.txtChatBeo);
        txtTinhBot = (TextView) findViewById(R.id.txtTinhBot);


        ibtnBackDetail = (ImageButton) findViewById(R.id.ibtnBackDetail);
        ibtnTymDetail = (ImageButton) findViewById(R.id.ibtnTymDetail);

        txtSoPhanAnDetail = (TextView) findViewById(R.id.txtSoPhanAnDetail);
        ibtnGiamSoLuong = (ImageButton) findViewById(R.id.ibtnGiamSoLuong);
        ibtnTangSoLuong = (ImageButton) findViewById(R.id.ibtnTangSoLuong);
        edtSoPhanAnDetail = (EditText) findViewById(R.id.edtSoPhanAnDetail);

        txtAnHienThongTin = (TextView) findViewById(R.id.txtAnHienThongTin);
        ibtnAnHienThongTin = (ImageButton) findViewById(R.id.ibtnAnHienThongTin);
        linearAnHienThongTinDinhDuong = (LinearLayout) findViewById(R.id.linearAnHienThongTinDinhDuong);
        listThongTinDinhDuong = (LinearLayout) findViewById(R.id.listThongTinDinhDuong);
    }
}