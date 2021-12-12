package com.example.ltnc20211.quanly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ltnc20211.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginQLActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_qlactivity);

        TextView login_to_register = findViewById(R.id.text_goto_register);
        TextInputEditText log_email = findViewById(R.id.input_login_email_ql);
        TextInputEditText log_password = findViewById(R.id.input_login_password_ql);
        Button btn_login = findViewById(R.id.btn_login_ql_to_home);


        // kiểm tra xem tài khaorn hiện có đang đăng nhập hay ko

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null ) {
            Intent auto_log = new Intent(LoginQLActivity.this, HomeQLActivity.class );
            startActivity(auto_log);
            finish();
        } else {

            //click đăng nhập ứng dụng
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    firebaseAuth.signInWithEmailAndPassword(log_email.getText().toString(),log_password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(LoginQLActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(LoginQLActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            });
        }






        // chuyển sang trang đăng ký
        login_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log_to_reg = new Intent(LoginQLActivity.this, RegisterQLActivity.class);
                startActivity(log_to_reg);
            }
        });
    }
}