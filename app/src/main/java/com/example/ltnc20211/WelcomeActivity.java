package com.example.ltnc20211;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ltnc20211.quanly.LoginQLActivity;
import com.example.ltnc20211.sinhvien.LoginSVActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // khởi tạo các biến
        Button button_login_ql = findViewById(R.id.btn_login_ql);
        Button button_login_sv = findViewById(R.id.btn_login_sv);

        // click button quản lý chuyển sang trang quản lý của giáo viên
        button_login_ql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log_ql = new Intent(WelcomeActivity.this, LoginQLActivity.class);
                startActivity(log_ql);
            }
        });

        // click button Sinh viên chuyển sang trang đăng nhập của sinh viên
        button_login_sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log_sv = new Intent(WelcomeActivity.this, LoginSVActivity.class);
                startActivity(log_sv);
            }
        });
    }
}