package com.example.ltnc20211.quanly.qlgv;

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
import com.example.ltnc20211.quanly.model.GiangVien;
import com.example.ltnc20211.quanly.model.SinhVien;
import com.example.ltnc20211.quanly.qlsv.QLSVActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddGVActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    DatePickerDialog addpicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gvactivity);

        TextInputEditText add_maGV = findViewById(R.id.add_maGV);
        TextInputEditText add_tenGV = findViewById(R.id.add_tenGV);
        TextInputEditText add_gtGV = findViewById(R.id.add_gtGV);
        TextInputEditText add_dobGV = findViewById(R.id.add_dobGV);
        TextInputEditText add_dcGV = findViewById(R.id.add_dcGV);


        add_dobGV.setInputType(InputType.TYPE_NULL);
        add_dobGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                addpicker = new DatePickerDialog(AddGVActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                add_dobGV.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                addpicker.show();
            }
        });


        Button btn_add_gv = findViewById(R.id.btn_add_gv);
        Button btn_cancel_gv = findViewById(R.id.btn_cancel_gv);

        btn_add_gv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                firebaseAuth = FirebaseAuth.getInstance();
                databaseReference = firebaseDatabase.getReference();

                String maGV = add_maGV.getEditableText().toString();
                String tenGV = add_tenGV.getEditableText().toString();
                String dobGV = add_dobGV.getEditableText().toString();
                String gtGV = add_gtGV.getEditableText().toString();
                String dcGV = add_dcGV.getEditableText().toString();

                GiangVien giangVien = new GiangVien(maGV,tenGV,gtGV,dobGV,dcGV);

                if (maGV.trim().equals("")){
                    Toast.makeText(v.getContext(),"Mã GV Trống",Toast.LENGTH_LONG).show();
                    return;
                } else {
                    databaseReference.child("GiangVien").child(maGV).setValue(giangVien);
                    Toast.makeText(v.getContext(),"Thêm Thành Công",Toast.LENGTH_LONG).show();
                    Intent done_add = new Intent(v.getContext(), QLGVActivity.class);
                    startActivity(done_add);
                    finish();
                }



            }
        });





        btn_cancel_gv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}























