package com.example.ltnc20211;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ltnc20211.quanly.LoginQLActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splash_to_log = new Intent(SplashScreenActivity.this, LoginQLActivity.class);
                startActivity(splash_to_log);
            }
        }, 3000);
    }
}