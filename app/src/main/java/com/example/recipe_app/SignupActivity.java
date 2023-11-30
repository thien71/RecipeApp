package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recipe_app.model.NguoiDung;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    Button btnLogin, btnSignup;
    EditText edtMatKhau, edtTaiKhoan, edtXacNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtTaiKhoan = (EditText) findViewById(R.id.edtTaiKhoanSignup);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhauSignup);
        edtXacNhan = (EditText) findViewById(R.id.edtXacNhanMatKhau);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taiKhoan = edtTaiKhoan.getText().toString().trim();
                String matKhau = edtMatKhau.getText().toString().trim();
                String xacNhanMatKhau = edtXacNhan.getText().toString().trim();

                if (!matKhau.equals(xacNhanMatKhau)) {
                    Toast.makeText(SignupActivity.this, "Xác nhận mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    return;
                }
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference usersRef = rootRef.child("NguoiDung");
                usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean isUserExists = false;
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            String tenDangNhap = userSnapshot.child("tenDangNhap").getValue(String.class);
                            if (tenDangNhap != null && tenDangNhap.equals(taiKhoan)) {
                                Toast.makeText(SignupActivity.this, "Tên tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                                isUserExists = true;
                                break;
                            }
                        }
                        if (!isUserExists) {
                            int maNguoiDung = Integer.valueOf((int) (dataSnapshot.getChildrenCount() + 1));
                            NguoiDung nguoiDung = new NguoiDung(maNguoiDung, taiKhoan, matKhau);
                            usersRef.child(String.valueOf(maNguoiDung)).setValue(nguoiDung);

                            Toast.makeText(SignupActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(SignupActivity.this, "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}