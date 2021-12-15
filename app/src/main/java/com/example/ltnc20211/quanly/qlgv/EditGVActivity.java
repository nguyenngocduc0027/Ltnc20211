package com.example.ltnc20211.quanly.qlgv;

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
import com.example.ltnc20211.quanly.model.GiangVien;
import com.example.ltnc20211.quanly.model.SinhVien;
import com.example.ltnc20211.quanly.qlsv.EditSVActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class EditGVActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    DatePickerDialog editpicker;

    private  TextInputEditText edit_maGV,edit_tenGV, edit_gtGV,edit_dobGV,edit_dcGV;
    private Button btn_edit_gv,btn_cancel_gv;
    private GiangVien giangVien;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_gvactivity);

        edit_maGV = findViewById(R.id.edit_maGV);
        edit_tenGV = findViewById(R.id.edit_tenGV);
        edit_gtGV = findViewById(R.id.edit_gtGV);
        edit_dobGV = findViewById(R.id.edit_dobGV);
        edit_dcGV = findViewById(R.id.edit_dcGV);
        btn_edit_gv = findViewById(R.id.btn_edit_gv);
        btn_cancel_gv = findViewById(R.id.btn_cancel_gv);


        edit_dobGV.setInputType(InputType.TYPE_NULL);
        edit_dobGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                editpicker = new DatePickerDialog(EditGVActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                edit_dobGV.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                editpicker.show();
            }
        });

        // lấy gói data từ màn hình hiển thị sinh viên
        Intent getdata = getIntent();
        // truyền vào khóa của gói để nhận dữ liệu đúng
        giangVien = (GiangVien) getdata.getSerializableExtra("GIANGVIEN");
        if (giangVien != null) {
            // đưa thông tin từ gói nhận đc hiển thị lên hinttext của edit text
            edit_maGV.setText(giangVien.getMaGV()+"");
            edit_tenGV.setText(giangVien.getTenGV());
            edit_gtGV.setText(giangVien.getGtGV());
            edit_dobGV.setText(giangVien.getDobGV()+"");
            edit_dcGV.setText(giangVien.getDcGV()+"");
        } else {
            Toast.makeText(getApplicationContext(),"Error Load Data", Toast.LENGTH_LONG).show();
        }


        // Cập nhật dữ liệu
        btn_edit_gv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                firebaseAuth = FirebaseAuth.getInstance();
                databaseReference = firebaseDatabase.getReference();

                String maGV = edit_maGV.getEditableText().toString();
                String tenGV = edit_tenGV.getEditableText().toString();
                String gtGV = edit_gtGV.getEditableText().toString();
                String dobGV = edit_dobGV.getEditableText().toString();
                String dcGV = edit_dcGV.getEditableText().toString();

                giangVien = new GiangVien(maGV,tenGV,gtGV,dobGV,dcGV);

                databaseReference.child("GiangVien").child(giangVien.getMaGV()).setValue(giangVien);
                finish();
                Toast.makeText(v.getContext(),"Cập Nhật Thành Công",Toast.LENGTH_LONG).show();
            }
        });


        //Hủy thao tác back về màn hình cũ
        btn_cancel_gv.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                // khi hủy thao tác nếu muốn dữ lại dữ liệu cũ thì thêm phần hiển thị
                if (giangVien != null) {
                    // đưa thông tin từ gói nhận đc hiển thị lên hinttext của edit text
                    edit_maGV.setText(giangVien.getMaGV()+"");
                    edit_tenGV.setText(giangVien.getTenGV());
                    edit_gtGV.setText(giangVien.getGtGV());
                    edit_dobGV.setText(giangVien.getDobGV()+"");
                    edit_dcGV.setText(giangVien.getDcGV()+"");
                }
                finish();
            }
        });

    }
}