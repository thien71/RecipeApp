package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recipe_app.model.DanhMucCongThuc;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText edtMatKhau, edtTaiKhoan;
    Button btnLogin, btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtTaiKhoan = (EditText) findViewById(R.id.edtTaiKhoan);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference usersRef = rootRef.child("NguoiDung");
                usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean isUserFound = false;
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            String tenDangNhap = userSnapshot.child("tenDangNhap").getValue(String.class);
                            String matKhau = userSnapshot.child("matKhau").getValue(String.class);

                            if (tenDangNhap != null && matKhau != null) {
                                if (tenDangNhap.equals(edtTaiKhoan.getText().toString().trim()) && matKhau.equals(edtMatKhau.getText().toString().trim())) {
                                    int maNguoiDung = userSnapshot.child("maNguoiDung").getValue(Integer.class);
                                    int quyen = userSnapshot.child("quyen").getValue(Integer.class);

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("maNguoiDung", maNguoiDung);
                                    intent.putExtra("quyen", quyen);
                                    startActivity(intent);
                                    return;
                                }
                            }
                        }
                        if (!isUserFound) {
                            Toast.makeText(LoginActivity.this, "Tài khoản và mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(LoginActivity.this, "Đã xảy ra lỗi khi đăng nhập", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}