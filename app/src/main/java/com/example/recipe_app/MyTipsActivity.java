package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyTipsActivity extends AppCompatActivity {
    private RecyclerView rcvMyTips;
    private MyTipsAdapter myTipsAdapter;
    private ImageButton ibtnBack;

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

//        myTipsAdapter = new MyTipsAdapter();
        LinearLayoutManager myTipsLinearLayoutManager = new LinearLayoutManager(this);

        rcvMyTips.setLayoutManager(myTipsLinearLayoutManager);
        rcvMyTips.setFocusable(false);
        rcvMyTips.setNestedScrollingEnabled(false);

//        myTipsAdapter.setMyTipsList(getListMyTips());
//
        rcvMyTips.setAdapter(myTipsAdapter);

        myTipsAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MyTips selectMyTips = myTipsAdapter.getItem(position);
                Intent intent = new Intent(MyTipsActivity.this, DetailActivity.class);
                intent.putExtra("idTenMon", selectMyTips.getTen());
                intent.putExtra("idHinh", selectMyTips.getHinh());
                startActivity(intent);
            }
        });
    }

//    private List<MyTips> getListMyTips() {
//        List<MyTips> arrayMyTips = new ArrayList<>();
//
//        arrayMyTips.add(new MyTips("Tôi nghĩ nên để nó sôi thêm 5 phút nữa.", 0, "Súp bánh bao chay", "3 giờ trước"));
//        arrayMyTips.add(new MyTips("Tôi nghĩ bạn đã cho thừa đường.", 0, "Bánh phô mai", "8 giờ trước", R.drawable.img_banh_pho_mai));
//
//        return arrayMyTips;
//    }
}