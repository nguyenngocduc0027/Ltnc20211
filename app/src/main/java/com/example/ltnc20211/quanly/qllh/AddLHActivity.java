package com.example.ltnc20211.quanly.qllh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ltnc20211.R;
import com.example.ltnc20211.quanly.model.LopHoc;
import com.example.ltnc20211.quanly.model.MonHoc;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddLHActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    MonHoc monHoc;


    TimePickerDialog add_time_picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lhactivity);

        TextInputEditText add_maLH_lh = findViewById(R.id.add_maLH);
//        TextInputEditText add_tenHP_lh = findViewById(R.id.add_tenHP_lh);
        Spinner add_tenHP_lh = findViewById(R.id.add_tenHP_lh);
//        TextInputEditText add_maHP_lh = findViewById(R.id.add_maHP_lh);
//        TextInputEditText add_tenGV_lh = findViewById(R.id.add_tenGV_lh);
        Spinner add_tenGV_lh = findViewById(R.id.add_tenGV_lh);
        TextInputEditText add_soPH_lh = findViewById(R.id.add_soPH_lh);
        TextInputEditText add_slSV_lh = findViewById(R.id.add_slSV_lh);
        TextInputEditText add_tgBatDau = findViewById(R.id.add_tgBatDau);
        TextInputEditText add_tgKetThuc = findViewById(R.id.add_tgKetThuc);


        //Spiner tên học phần
        List<String> tenHP_lh_spinner = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("MonHoc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot chilmaSnapShot:snapshot.getChildren()) {
                    String add_tenHP_lh_sp = chilmaSnapShot.child("tenHP").getValue(String.class);
                    tenHP_lh_spinner.add(add_tenHP_lh_sp);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AddLHActivity.this,android.R.layout.simple_spinner_item,tenHP_lh_spinner);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                add_tenHP_lh.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Spiner tên giảng viên
        List<String> tenGV_lh_spinner = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("GiangVien").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot chilGVSnapShot:snapshot.getChildren()) {
                    String add_tenGV_lh_sp = chilGVSnapShot.child("tenGV").getValue(String.class);
                    tenGV_lh_spinner.add(add_tenGV_lh_sp);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(AddLHActivity.this,android.R.layout.simple_spinner_item,tenGV_lh_spinner);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                add_tenGV_lh.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        add_tgBatDau.setInputType(InputType.TYPE_NULL);
        add_tgBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                final boolean is24HView = true;
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minute =cldr.get(Calendar.MINUTE);
                // time picker dialog
                add_time_picker = new TimePickerDialog(AddLHActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        add_tgBatDau.setText(hourOfDay + ":" + minute);
                    }
                },hour,minute,is24HView);
                add_time_picker.show();
            }
        });

        add_tgKetThuc.setInputType(InputType.TYPE_NULL);
        add_tgKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                final boolean is24HView = true;
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minute =cldr.get(Calendar.MINUTE);
                // time picker dialog
                add_time_picker = new TimePickerDialog(AddLHActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        add_tgKetThuc.setText(hourOfDay + ":" + minute);
                    }
                },hour,minute,is24HView);
                add_time_picker.show();
            }
        });


        Button btn_add_lh =findViewById(R.id.btn_add_lh);
        Button btn_cancel_lh = findViewById(R.id.btn_cancel_lh);

        btn_add_lh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference();

                String maLH = add_maLH_lh.getEditableText().toString().toUpperCase();
                String tenHP = add_tenHP_lh.getSelectedItem().toString().toUpperCase();
                String tenGV = add_tenGV_lh.getSelectedItem().toString().toUpperCase();
                String soPH = add_soPH_lh.getEditableText().toString().toUpperCase();
                String slSV = add_slSV_lh.getEditableText().toString();
                String tgBatDau = add_tgBatDau.getEditableText().toString();
                String tgKetThuc = add_tgKetThuc.getEditableText().toString();

                LopHoc lopHoc = new LopHoc(maLH, tenHP, tenGV, soPH, slSV, tgBatDau, tgKetThuc);

                if (maLH.trim().equals("")){
                    Toast.makeText(v.getContext(),"Mã Lớp Học Trống", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    databaseReference.child("LopHoc").child(maLH).setValue(lopHoc);
                    Toast.makeText(v.getContext(),"Thêm Thành Công", Toast.LENGTH_LONG).show();
                    Intent done_add_lh = new Intent(v.getContext(),QLLHActivity.class);
                    startActivity(done_add_lh);
                    finish();
                }
            }
        });

        btn_cancel_lh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}