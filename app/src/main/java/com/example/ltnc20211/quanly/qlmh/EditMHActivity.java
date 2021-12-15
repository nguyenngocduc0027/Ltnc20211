package com.example.ltnc20211.quanly.qlmh;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ltnc20211.R;
import com.example.ltnc20211.quanly.model.MonHoc;
import com.example.ltnc20211.quanly.model.SinhVien;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditMHActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    private TextInputEditText edit_maHP,edit_tenHP,edit_tc;
    private Button btn_edit_mh,btn_cancel_edit_mh;
    private MonHoc monHoc;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mhactivity);

        edit_maHP = findViewById(R.id.edit_maHP);
        edit_tenHP = findViewById(R.id.edit_tenHP);
        edit_tc = findViewById(R.id.edit_tc);
        btn_edit_mh =findViewById(R.id.btn_edit_mh);
        btn_cancel_edit_mh = findViewById(R.id.btn_cancel_edit_mh);

        Intent getdata_mh =getIntent();

        monHoc = (MonHoc) getdata_mh.getSerializableExtra("MONHOC");
        if (monHoc != null) {
            // đưa thông tin từ gói nhận đc hiển thị lên hinttext của edit text
            edit_maHP.setText(monHoc.getMaHP()+"");
            edit_tenHP.setText(monHoc.getTenHP());
            edit_tc.setText(monHoc.getTc());
        } else {
            Toast.makeText(getApplicationContext(),"Error Load Data Sinh Vien", Toast.LENGTH_LONG).show();
        }


        // Cập nhật dữ liệu
        btn_edit_mh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                firebaseAuth = FirebaseAuth.getInstance();
                databaseReference = firebaseDatabase.getReference();

                String maHP = edit_maHP.getEditableText().toString();
                String tenHP = edit_tenHP.getEditableText().toString();
                String tc = edit_tc.getEditableText().toString();

                monHoc = new MonHoc(maHP,tenHP,tc);

                databaseReference.child("MonHoc").child(monHoc.getMaHP()).setValue(monHoc);
                finish();
                Toast.makeText(v.getContext(),"Cập Nhật Thành Công",Toast.LENGTH_LONG).show();
            }
        });
    }
}