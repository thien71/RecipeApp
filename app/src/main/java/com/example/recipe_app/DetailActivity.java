package com.example.recipe_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    ImageView imgHinh;
    ImageButton ibtnBackDetail;
    TextView txtTenMonTieuDem, txtTenMon;

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
    }

    private void Mapping() {
        imgHinh = (ImageView) findViewById(R.id.imgHinhDetail);
        txtTenMon = (TextView) findViewById(R.id.txtTenMonDetail);
        txtTenMonTieuDem = (TextView) findViewById(R.id.txtTenMonDetailTopic);

        ibtnBackDetail = (ImageButton) findViewById(R.id.ibtnBackDetail);
    }
}