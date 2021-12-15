package com.example.ltnc20211.quanly.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.ltnc20211.R;
import com.example.ltnc20211.quanly.model.SinhVien;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class EditSVActivity extends AppCompatActivity {


    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    DatePickerDialog editpicker;

    private  TextInputEditText edit_mssv,edit_name,edit_email,edit_dob,edit_sdt,edit_dc,edit_password;
    private Button btn_edit_sv,btn_cancel;
    private SinhVien sinhVien;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_svactivity);

        edit_mssv = findViewById(R.id.edit_mssv);
        edit_name = findViewById(R.id.edit_name);
        edit_email = findViewById(R.id.edit_email);
        edit_dob = findViewById(R.id.edit_dob);
        edit_sdt = findViewById(R.id.edit_sdt);
        edit_dc = findViewById(R.id.edit_dc);
        edit_password = findViewById(R.id.edit_password);
        btn_edit_sv = findViewById(R.id.btn_edit_sv);
        btn_cancel = findViewById(R.id.btn_cancel);


        edit_dob.setInputType(InputType.TYPE_NULL);
        edit_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                editpicker = new DatePickerDialog(EditSVActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                edit_dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                editpicker.show();
            }
        });


        // lấy gói data từ màn hình hiển thị sinh viên
        Intent getdata = getIntent();
        // truyền vào khóa của gói để nhận dữ liệu đúng
        sinhVien = (SinhVien) getdata.getSerializableExtra("SINHVIEN");
        if (sinhVien != null) {
            // đưa thông tin từ gói nhận đc hiển thị lên hinttext của edit text
            edit_mssv.setText(sinhVien.getMssv()+"");
            edit_name.setText(sinhVien.getName());
            edit_email.setText(sinhVien.getEmail());
            edit_dob.setText(sinhVien.getDob()+"");
            edit_sdt.setText(sinhVien.getSdt()+"");
            edit_dc.setText(sinhVien.getDc());
            edit_password.setText(sinhVien.getPassword()+"");
        } else {
            Toast.makeText(getApplicationContext(),"Error Load Data Sinh Vien", Toast.LENGTH_LONG).show();
        }


        // Cập nhật dữ liệu
        btn_edit_sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                firebaseAuth = FirebaseAuth.getInstance();
                databaseReference = firebaseDatabase.getReference();

                String mssv = edit_mssv.getEditableText().toString();
                String name = edit_name.getEditableText().toString();
                String dob = edit_dob.getEditableText().toString();
                String email = edit_email.getEditableText().toString();
                String sdt = edit_sdt.getEditableText().toString();
                String dc = edit_dc.getEditableText().toString();
                String password = edit_password.getEditableText().toString();

                sinhVien = new SinhVien(mssv,name,dob,email,sdt,dc,password);

                databaseReference.child("SinhVien").child("users").child(sinhVien.getMssv()).setValue(sinhVien);
                finish();
                Toast.makeText(v.getContext(),"Cập Nhật Thành Công",Toast.LENGTH_LONG).show();
            }
        });


        //Hủy thao tác back về màn hình cũ
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                // khi hủy thao tác nếu muốn dữ lại dữ liệu cũ thì thêm phần hiển thị
                if (sinhVien != null) {
                    // đưa thông tin từ gói nhận đc hiển thị lên hinttext của edit text
                    edit_mssv.setText(sinhVien.getMssv()+"");
                    edit_name.setText(sinhVien.getName());
                    edit_email.setText(sinhVien.getEmail());
                    edit_dob.setText(sinhVien.getDob()+"");
                    edit_sdt.setText(sinhVien.getSdt()+"");
                    edit_dc.setText(sinhVien.getDc());
                    edit_password.setText(sinhVien.getPassword()+"");
                }
                finish();
            }
        });
    }
}