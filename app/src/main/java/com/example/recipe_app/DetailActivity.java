package com.example.recipe_app;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    ImageView imgHinh;
    ImageButton ibtnBackDetail, ibtnTymDetail, ibtnAnHienThongTin, ibtnGiamSoLuong, ibtnTangSoLuong;
    TextView txtTenMonTieuDem, txtTenMon, txtAnHienThongTin, txtSoPhanAnDetail;
    EditText edtSoPhanAnDetail;

    LinearLayout linearAnHienThongTinDinhDuong, listThongTinDinhDuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Mapping();

        int itemHinh = getIntent().getIntExtra("idHinh", 0);
        String itemTenMon = getIntent().getStringExtra("idTenMon");

        // Set nội dung cho detail

        imgHinh.setImageResource(itemHinh);
        txtTenMon.setText(itemTenMon);
        txtTenMonTieuDem.setText(itemTenMon);

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

        // Tăng giảm số lượng

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
        imgHinh = (ImageView) findViewById(R.id.imgHinhDetail);
        txtTenMon = (TextView) findViewById(R.id.txtTenMonDetail);
        txtTenMonTieuDem = (TextView) findViewById(R.id.txtTenMonDetailTopic);

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