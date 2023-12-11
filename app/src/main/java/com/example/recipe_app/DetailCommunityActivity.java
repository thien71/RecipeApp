package com.example.recipe_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipe_app.model.BaiDangCongDong;
import com.squareup.picasso.Picasso;

import dalvik.annotation.optimization.CriticalNative;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailCommunityActivity extends AppCompatActivity {
    TextView txtTenNguoiDung, txtTenTieuDe, txtNoiDung, txtSoLike, txtSoBinhLuan, txtTenMon;
    CircleImageView cirAvatar;
    ImageView imgHinh;
    ImageButton ibtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_community);

        Mapping();

        Intent intent = getIntent();
        if (intent != null) {
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

        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
    }
}