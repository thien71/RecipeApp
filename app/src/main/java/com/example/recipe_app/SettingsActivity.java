package com.example.recipe_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    ListView lvSettings1;
    ListView lvSettings2;
    ListView lvSettings3;
    ListView lvSettings4;
    ArrayList<String> lv1;
    ArrayList<String> lv2;
    ArrayList<String> lv3;
    ArrayList<String> lv4;
    ImageButton ibtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        lvSettings1 = (ListView) findViewById(R.id.settinglist1);
        lvSettings2 = (ListView) findViewById(R.id.settinglist2);
        lvSettings3 = (ListView) findViewById(R.id.settinglist3);
        lvSettings4 = (ListView) findViewById(R.id.settinglist4);

        ibtnBack = (ImageButton) findViewById(R.id.ibtnBackSettings);
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lv1 = new ArrayList<>();
        lv2 = new ArrayList<>();
        lv3 = new ArrayList<>();
        lv4 = new ArrayList<>();

        lv1.add("Tự động phát video");
        lv1.add("Chế độ ăn kiên");
        lv1.add("Thông báo");

        lv2.add("Email hỗ trợ");
        lv2.add("Đánh giá ứng dụng");
        lv2.add("FQA");

        lv3.add("Thỏa thuận người dùng");
        lv3.add("Chính sách bảo mật");
        lv3.add("Tải dữ liệu người dùng");
        lv3.add("Thông tin về FLAVOR");

        lv4.add("Đăng xuất");
        lv4.add("Xóa tài khoản");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(SettingsActivity.this, android.R.layout.simple_list_item_1, lv1);
        lvSettings1.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(SettingsActivity.this, android.R.layout.simple_list_item_1, lv2);
        lvSettings2.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(SettingsActivity.this, android.R.layout.simple_list_item_1, lv3);
        lvSettings3.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(SettingsActivity.this, android.R.layout.simple_list_item_1, lv4);
        lvSettings4.setAdapter(adapter4);
    }
}