package com.example.ltnc20211.quanly.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ltnc20211.R;
import com.example.ltnc20211.quanly.LoginQLActivity;
import com.example.ltnc20211.quanly.RegisterQLActivity;
import com.example.ltnc20211.quanly.model.SinhVien;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSVActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_svactivity);


        TextInputEditText add_mssv = findViewById(R.id.add_mssv);
        TextInputEditText add_name = findViewById(R.id.add_name);
        TextInputEditText add_email = findViewById(R.id.add_email);
        TextInputEditText add_dob = findViewById(R.id.add_dob);
        TextInputEditText add_sdt = findViewById(R.id.add_sdt);
        TextInputEditText add_dc = findViewById(R.id.add_dc);
        TextInputEditText add_password = findViewById(R.id.add_password);

        Button btn_add_sv = findViewById(R.id.btn_add_sv);
        Button btn_cancel = findViewById(R.id.btn_cancel);


        btn_add_sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                firebaseAuth = FirebaseAuth.getInstance();
                databaseReference = firebaseDatabase.getReference();

                String mssv = add_mssv.getEditableText().toString();
                String name = add_name.getEditableText().toString();
                String dob = add_dob.getEditableText().toString();
                String email = add_email.getEditableText().toString();
                String sdt = add_sdt.getEditableText().toString();
                String dc = add_dc.getEditableText().toString();
                String password = add_password.getEditableText().toString();

                SinhVien sinhVien = new SinhVien(mssv,name,dob,email,sdt,dc,password);

                databaseReference.child("SinhVien").child("users").child(mssv).setValue(sinhVien);
                Toast.makeText(v.getContext(),"Thêm Thành Công",Toast.LENGTH_LONG).show();
                Intent done_add = new Intent(v.getContext(), QLSVActivity.class);
                startActivity(done_add);

            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}