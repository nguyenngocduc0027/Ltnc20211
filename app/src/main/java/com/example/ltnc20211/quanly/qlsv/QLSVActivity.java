package com.example.ltnc20211.quanly.qlsv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ltnc20211.R;
import com.example.ltnc20211.quanly.adapter.SinhVienAdapter;
import com.example.ltnc20211.quanly.model.SinhVien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QLSVActivity extends AppCompatActivity {

    private ListView list_sv;
    private ArrayList<SinhVien> sinhVienArrayList;
    private SinhVienAdapter adapter;
    private Button btn_add_sv_big;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsvactivity);


        list_sv = findViewById(R.id.list_sv);
        btn_add_sv_big = findViewById(R.id.btn_add_sv_big);


        // tạo danh sách sinh viên
        sinhVienArrayList = new ArrayList<>();
//        sinhVienArrayList.add(new SinhVien("20170027","NguyenNgocDuc","16/05/1999","ngocduc.bk.hust@gmail.com","0378049798","Thanh Hoa","1234567"));
//        sinhVienArrayList.add(new SinhVien("20170028","NguyenNgocDuc","16/05/1999","ngocduc.bk.hust@gmail.com","0378049798","Thanh Hoa","1234567"));
//        sinhVienArrayList.add(new SinhVien("20170029","NguyenNgocDuc","16/05/1999","ngocduc.bk.hust@gmail.com","0378049798","Thanh Hoa","1234567"));
//        sinhVienArrayList.add(new SinhVien("20170030","NguyenNgocDuc","16/05/1999","ngocduc.bk.hust@gmail.com","0378049798","Thanh Hoa","1234567"));
//        sinhVienArrayList.add(new SinhVien("20170031","NguyenNgocDuc","16/05/1999","ngocduc.bk.hust@gmail.com","0378049798","Thanh Hoa","1234567"));
        GetData();





        // gọi custom adapter để gán cho listview
        //this là layout này, custom_listview_item là layout của từng sinh viên
        // sinhvien_array_list là list data sinh viên được trả về từ firebase
        adapter = new SinhVienAdapter(this,R.layout.custom_listview_item,sinhVienArrayList);


        // tạo adapter cho listview
        list_sv.setAdapter(adapter);



        // Gọi layout thêm sinh viên
        btn_add_sv_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),AddSVActivity.class);
                startActivity(intent);
            }
        });

    }

    // Lấy danh sách sinh viên từ firebase
    private void GetData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().child("SinhVien").child("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // cập nhật lại dữ liệu mới lên list_sv và xóa dữ liệu cũ
                adapter.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    // chuyển đổi dữ liệu qua class SinhVien
                    SinhVien sinhVien = dataSnapshot.getValue(SinhVien.class);
                    // thêm sinh viên vào list_sv
                    assert sinhVien != null;
                    sinhVien.setMssv(dataSnapshot.getKey());
                    adapter.add(sinhVien);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Fail" + error.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

}