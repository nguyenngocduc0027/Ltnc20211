package com.example.ltnc20211.quanly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ltnc20211.R;
import com.example.ltnc20211.quanly.qlsv.AddSVActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class RegisterQLActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    DatePickerDialog addpicker_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_qlactivity);


        TextView register_to_login = findViewById(R.id.text_goto_login);
        TextInputEditText reg_email = findViewById(R.id.input_email_ql);
        TextInputEditText reg_name = findViewById(R.id.input_name_ql);
        TextInputEditText reg_age = findViewById(R.id.input_age_ql);
        TextInputEditText reg_pass = findViewById(R.id.input_password_ql);

        Button btn_reg_acc_ql = findViewById(R.id.btn_register_ql_to_login);

        ImageButton show_pass_reg = findViewById(R.id.show_pass_reg);
        show_pass_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show_pass_reg.isClickable()){
                    reg_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    reg_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });



        reg_age.setInputType(InputType.TYPE_NULL);
        reg_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                addpicker_reg = new DatePickerDialog(RegisterQLActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                reg_age.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                addpicker_reg.show();
            }
        });



        // đăng ký account
        btn_reg_acc_ql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                firebaseAuth = FirebaseAuth.getInstance();
                databaseReference = firebaseDatabase.getReference();

                String email = reg_email.getEditableText().toString();
                String name = reg_name.getEditableText().toString();
                String age = reg_age.getEditableText().toString();
                String password = reg_pass.getEditableText().toString();

                QuanLyHelperClass quanLyHelperClass = new QuanLyHelperClass(email,name,age,password);
                Intent done_register = new Intent(RegisterQLActivity.this,LoginQLActivity.class);
                firebaseAuth.createUserWithEmailAndPassword(reg_email.getText().toString(),reg_pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    databaseReference.child("QuanLy").child("users").child(firebaseAuth.getUid()).setValue(quanLyHelperClass);
                                    Toast.makeText(RegisterQLActivity.this,"Đăng Ký Thành Công",Toast.LENGTH_LONG).show();
                                    startActivity(done_register);
                                } else {
                                    Toast.makeText(RegisterQLActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });



        // click chuyển về trang đăng nhập nếu có tài khoản rồi
        register_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg_to_log = new Intent(RegisterQLActivity.this, LoginQLActivity.class);
                startActivity(reg_to_log);
            }
        });
    }
}