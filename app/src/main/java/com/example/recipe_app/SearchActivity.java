package com.example.recipe_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.adapter.SearchAdapter;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView rcvSearch;
    SearchAdapter adapter;

    private ImageButton ibtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rcvSearch = (RecyclerView) findViewById(R.id.rcvSearch);

        ibtnBack = (ImageButton) findViewById(R.id.ibtnBack);
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        String[] ten = {"Bữa tối đơn giản", "Dưới 5 thành phần", "Dưới 30 phút", "Gà", "Rẻ", "Tráng miệng"};
        int[] hinh = {R.drawable.img_sup_ga, R.drawable.img_mi_tom, R.drawable.img_clock,
                R.drawable.img_dui_ga, R.drawable.img_vnd, R.drawable.img_cookie};


        adapter = new SearchAdapter(this, hinh, ten);
        LinearLayoutManager searchGridLayoutManager = new GridLayoutManager(this, 2);
        rcvSearch.setLayoutManager(searchGridLayoutManager);
        rcvSearch.setFocusable(false);
        rcvSearch.setNestedScrollingEnabled(false);
        rcvSearch.setAdapter(adapter);
    }
}