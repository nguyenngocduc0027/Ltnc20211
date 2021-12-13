package com.example.ltnc20211.quanly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ltnc20211.R;
import com.example.ltnc20211.quanly.qlsv.QLSVActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeQLActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_qlactivity);


        Button logout = findViewById(R.id.btn_logout);

        firebaseAuth = FirebaseAuth.getInstance();

        Intent log_out = new Intent(this,LoginQLActivity.class);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(log_out);
            }
        });



        Button go_to_ql_sinhvien = findViewById(R.id.btn_ql_sv);
        go_to_ql_sinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_page_ql_sv = new Intent(v.getContext(), QLSVActivity.class);
                startActivity(go_page_ql_sv);
            }
        });
    }
}