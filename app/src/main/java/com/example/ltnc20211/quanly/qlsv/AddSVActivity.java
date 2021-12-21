package com.example.ltnc20211.quanly.qlsv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.ltnc20211.R;
import com.example.ltnc20211.quanly.LoginQLActivity;
import com.example.ltnc20211.quanly.RegisterQLActivity;
import com.example.ltnc20211.quanly.model.SinhVien;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class AddSVActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    DatePickerDialog addpicker;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String phonePattern = "[0-9]";



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



        add_dob.setInputType(InputType.TYPE_NULL);
        add_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                addpicker = new DatePickerDialog(AddSVActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                add_dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                addpicker.show();
            }
        });



        Button btn_add_sv = findViewById(R.id.btn_add_sv);
        Button btn_cancel = findViewById(R.id.btn_cancel);


        btn_add_sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference();

                String mssv = add_mssv.getEditableText().toString();
                String name = add_name.getEditableText().toString();
                String dob = add_dob.getEditableText().toString();
                String email = add_email.getEditableText().toString();
                String sdt = add_sdt.getEditableText().toString();
                String dc = add_dc.getEditableText().toString().toUpperCase();
                String password = add_password.getEditableText().toString();

                SinhVien sinhVien = new SinhVien(mssv,name,dob,email,sdt,dc,password);

                if (mssv.isEmpty()){
                    Toast.makeText(v.getContext(),"MSSV Trống",Toast.LENGTH_LONG).show();
                    return;
                }
                if (mssv.trim().length() != 8){
                    Toast.makeText(v.getContext(),"MSSV Chưa Đủ",Toast.LENGTH_LONG).show();
                    return;
                }
                if (email.trim().matches(emailPattern)){
                    Toast.makeText(v.getContext(),"Email sai",Toast.LENGTH_LONG).show();
                    return;
                }
                if (sdt.trim().matches(phonePattern)  ){
                    Toast.makeText(v.getContext(),"Số Điện Thoại Sai",Toast.LENGTH_LONG).show();
                    return;
                }
                if(sdt.trim().length() == 10){
                    Toast.makeText(v.getContext(),"Số Điện Thoại Chưa Chuẩn Định Dạng",Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.trim().length() < 6) {
                    Toast.makeText(v.getContext(),"Mật Khẩu Tối Thiểu 8 Ký Tự",Toast.LENGTH_LONG).show();
                    return;
                } else {
                    databaseReference.child("SinhVien").child("users").child(mssv).setValue(sinhVien);
                    Toast.makeText(v.getContext(),"Thêm Thành Công",Toast.LENGTH_LONG).show();
                    Intent done_add = new Intent(v.getContext(), QLSVActivity.class);
                    startActivity(done_add);
                    finish();
                }
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