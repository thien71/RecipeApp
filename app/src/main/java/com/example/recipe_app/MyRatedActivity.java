package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRatedActivity extends AppCompatActivity {
    private RecyclerView rcvMyRated;
    private MyRatedAdapter myRatedAdapter;
    private ImageButton ibtnBack;

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


        myRatedAdapter = new MyRatedAdapter();
        LinearLayoutManager myRatedLinearLayoutManager = new LinearLayoutManager(this);

        rcvMyRated.setLayoutManager(myRatedLinearLayoutManager);
        rcvMyRated.setFocusable(false);
        rcvMyRated.setNestedScrollingEnabled(false);

        myRatedAdapter.setMyRatedList(getListMyRated());

        rcvMyRated.setAdapter(myRatedAdapter);

        myRatedAdapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MyRated selectMyRated = myRatedAdapter.getItem(position);
                Intent intent = new Intent(MyRatedActivity.this, DetailActivity.class);
                intent.putExtra("idTenMon", selectMyRated.getTen());
                intent.putExtra("idHinh", selectMyRated.getHinh());

                startActivity(intent);
            }
        });
    }

    private List<MyRated> getListMyRated() {
        List<MyRated> arrayMyRated = new ArrayList<>();
        arrayMyRated.add(new MyRated((R.drawable.img_sup_banh_bao_chay), "Súp bánh bao chay", "3 giờ trước", R.drawable.like));
        arrayMyRated.add(new MyRated((R.drawable.img_banh_pho_mai), "Bánh phô mai", "5 giờ trước", R.drawable.dislike));

        return arrayMyRated;
    }
}