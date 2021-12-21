package com.example.ltnc20211.quanly.qlmh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ltnc20211.R;
import com.example.ltnc20211.quanly.model.MonHoc;
import com.example.ltnc20211.quanly.model.SinhVien;
import com.example.ltnc20211.quanly.qlsv.QLSVActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMHActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mhactivity);

        TextInputEditText add_maHP = findViewById(R.id.add_maHP);
        TextInputEditText add_tenHP = findViewById(R.id.add_tenHP);
        TextInputEditText add_tc = findViewById(R.id.add_tc);

        Button btn_add_mh = findViewById(R.id.btn_add_mh);
        Button btn_cancel_mh = findViewById(R.id.btn_cancel_mh);

        btn_add_mh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                firebaseAuth = FirebaseAuth.getInstance();
                databaseReference = firebaseDatabase.getReference();

                String maHP = add_maHP.getEditableText().toString();
                String tenHP = add_tenHP.getEditableText().toString();
                String tc = add_tc.getEditableText().toString();

                MonHoc monHoc = new MonHoc(maHP,tenHP,tc);

                if (maHP.trim().equals("")){
                    Toast.makeText(v.getContext(),"Mã HP Trống",Toast.LENGTH_LONG).show();
                    return;
                } else {
                    databaseReference.child("MonHoc").child(maHP).setValue(monHoc);
                    Toast.makeText(v.getContext(),"Thêm Thành Công",Toast.LENGTH_LONG).show();
                    Intent done_add = new Intent(v.getContext(), QLMHActivity.class);
                    startActivity(done_add);
                    finish();
                }



            }
        });


        btn_cancel_mh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}