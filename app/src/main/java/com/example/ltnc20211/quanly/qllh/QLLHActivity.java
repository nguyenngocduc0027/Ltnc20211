package com.example.ltnc20211.quanly.qllh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ltnc20211.R;
import com.example.ltnc20211.quanly.adapter.LopHocAdapter;
import com.example.ltnc20211.quanly.adapter.SinhVienAdapter;
import com.example.ltnc20211.quanly.model.LopHoc;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class QLLHActivity extends AppCompatActivity {

    private ListView list_lh;
    private ArrayList<LopHoc> lopHocArrayList;
    private LopHocAdapter adapter;
    private Button btn_add_lh_big;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qllhactivity);

        list_lh = findViewById(R.id.list_lh);
        btn_add_lh_big = findViewById(R.id.btn_add_lh_big);

        // tạo danh sách
        lopHocArrayList = new ArrayList<>();
//        lopHocArrayList.add(new LopHoc("716809","ET2212","Text","Nguyễn Thi C","TC302","50","12h30","14h50"));
//        lopHocArrayList.add(new LopHoc("716809","ET2212","Text","Nguyễn Thi C","TC302","50","12h30","14h50"));
//        lopHocArrayList.add(new LopHoc("716809","ET2212","Text","Nguyễn Thi C","TC302","50","12h30","14h50"));
//        lopHocArrayList.add(new LopHoc("716809","ET2212","Text","Nguyễn Thi C","TC302","50","12h30","14h50"));
//        lopHocArrayList.add(new LopHoc("716809","ET2212","Text","Nguyễn Thi C","TC302","50","12h30","14h50"));
//        lopHocArrayList.add(new LopHoc("716809","ET2212","Text","Nguyễn Thi C","TC302","50","12h30","14h50"));
//        lopHocArrayList.add(new LopHoc("716809","ET2212","Text","Nguyễn Thi C","TC302","50","12h30","14h50"));

        GetDataLH();

        // gọi custom adapter để gán cho listview
        //this là layout này, custom_listview_item là layout của từng sinh viên
        // sinhvien_array_list là list data sinh viên được trả về từ firebase
        adapter = new LopHocAdapter(this,R.layout.custom_listview_lh_item,lopHocArrayList);


        // tạo adapter cho listView
        list_lh.setAdapter(adapter);



        // gọi layout thêm lớp học
        btn_add_lh_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),AddLHActivity.class);
                startActivity(intent);
            }
        });

    }

    // Lấy data từ firebase
    private void  GetDataLH(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("LopHoc");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();
                // cập nhật dữ liệu mới lên list

                for ( DataSnapshot dataSnapshot : snapshot.getChildren()){
                    //chuyển dữ liệu qua class Lớp Học
                    LopHoc lopHoc = dataSnapshot.getValue(LopHoc.class);
                    // Thêm lớp học vào list
                    assert  lopHoc != null;
                    lopHoc.setMaLH(dataSnapshot.getKey());
                    adapter.add(lopHoc);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), " Fail" + error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}