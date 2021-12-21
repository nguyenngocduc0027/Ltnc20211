package com.example.ltnc20211;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ltnc20211.quanly.HomeQLActivity;
import com.example.ltnc20211.quanly.LoginQLActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreenActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // kiểm tra xem tài khaorn hiện có đang đăng nhập hay ko

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null ) {
            Intent auto_log = new Intent(SplashScreenActivity.this, HomeQLActivity.class );
            startActivity(auto_log);
            finish();
        } else {


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent splash_to_log = new Intent(SplashScreenActivity.this, LoginQLActivity.class);
                    startActivity(splash_to_log);
                    finish();
                }
            }, 3000);
        }
    }
}