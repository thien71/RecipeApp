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

import com.example.recipe_app.adapter.NguyenLieuAdapter;
import com.example.recipe_app.model.NguyenLieu;
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
    EditText edtSoPhanAnDetail;

    WebView webView;
    LinearLayout linearAnHienThongTinDinhDuong, listThongTinDinhDuong;

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
                    String hinh = dataSnapshot.child("duongDanHinhAnh").getValue(String.class);
                    String linkVideo = dataSnapshot.child("video").child("duongDanVideo").getValue(String.class);
                    // Sau khi lấy dữ liệu từ Firebase, bạn có thể hiển thị nó trên giao diện của DetailActivity.
                    txtTenMon.setText(tieuDe);
                    txtTenMonTieuDem.setText(tieuDe);
                    txtMoTa.setText(moTa);
                    //Picasso.get().load(hinh).into(imgHinh);

                    webView.loadData(linkVideo, "text/html", "utf-8");
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.setWebChromeClient(new WebChromeClient());

                    //nguyenlieu
                    List<NguyenLieu> nguyenLieuList = new ArrayList<>();

                    for (DataSnapshot nguyenLieuSnapshot : dataSnapshot.child("nguyenLieu").getChildren()) {
                        String tenNguyenLieu = nguyenLieuSnapshot.child("tenNguyenLieu").getValue(String.class);
                        int soLuong = nguyenLieuSnapshot.child("soLuong").getValue(Integer.class);
                        String donVi = nguyenLieuSnapshot.child("donVi").getValue(String.class);

                        Log.d("THIEN", soLuong+"");

                        // Tạo đối tượng NguyenLieu từ thông tin lấy được và thêm vào List
                        NguyenLieu nguyenLieu = new NguyenLieu(tenNguyenLieu, soLuong, donVi);
                        nguyenLieuList.add(nguyenLieu);
                    }

                    RecyclerView recyclerViewNguyenLieu = findViewById(R.id.rcvNguyenLieu);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(DetailActivity.this);
                    recyclerViewNguyenLieu.setLayoutManager(layoutManager);

                    NguyenLieuAdapter nguyenLieuAdapter = new NguyenLieuAdapter(nguyenLieuList);
                    recyclerViewNguyenLieu.setAdapter(nguyenLieuAdapter);

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
                soPhanAn -= 1;
                edtSoPhanAnDetail.setText(soPhanAn +"");
                txtSoPhanAnDetail.setText(soPhanAn + " phần ăn");
            }
        });
        ibtnTangSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soPhanAn = Integer.parseInt(String.valueOf(edtSoPhanAnDetail.getText()));
                soPhanAn += 1;
                edtSoPhanAnDetail.setText(soPhanAn +"");
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
                String soLuong = s.toString();

                txtSoPhanAnDetail.setText(soLuong + " phần ăn");
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