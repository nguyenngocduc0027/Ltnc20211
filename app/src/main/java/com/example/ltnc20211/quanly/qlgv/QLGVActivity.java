package com.example.ltnc20211.quanly.qlgv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ltnc20211.R;
import com.example.ltnc20211.quanly.adapter.GiangVienAdapter;
import com.example.ltnc20211.quanly.adapter.MonHocAdapter;
import com.example.ltnc20211.quanly.model.GiangVien;
import com.example.ltnc20211.quanly.model.MonHoc;
import com.example.ltnc20211.quanly.model.SinhVien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QLGVActivity extends AppCompatActivity {
// Vũ Quang linh 
    private ListView list_gv;
    private ArrayList<GiangVien> giangVienArrayList;
    private GiangVienAdapter adapter;
    private Button btn_add_gv_big;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlgvactivity);

        list_gv = findViewById(R.id.list_gv);
        btn_add_gv_big = findViewById(R.id.btn_add_gv_big);


        // tạo danh sách sinh viên
        giangVienArrayList = new ArrayList<>();
//        giangVienArrayList.add(new GiangVien("tinh.pd","Pham Doan Tinh", "nam", "20/07/1975", "Ha Noi"));
//        giangVienArrayList.add(new GiangVien("tinh.pd","Pham Doan Tinh", "nam", "20/07/1975", "Ha Noi"));
        GetData_GV();

        // tạo adapter
        adapter = new GiangVienAdapter(this,R.layout.custom_listview_gv_item, giangVienArrayList);
        // adapter cho listview gv
        list_gv.setAdapter(adapter);

        btn_add_gv_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),AddGVActivity.class);
                startActivity(intent);
            }
        });
    }

        private void GetData_GV() {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference().child("GiangVien");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // cập nhật lại dữ liệu mới lên list_sv và xóa dữ liệu cũ
                    adapter.clear();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        // chuyển đổi dữ liệu qua class SinhVien
                        GiangVien giangVien = dataSnapshot.getValue(GiangVien.class);
                        // thêm sinh viên vào list_sv
                        assert giangVien != null;
                        giangVien.setMaGV(dataSnapshot.getKey());
                        adapter.add(giangVien);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "Fail" + error.toString(),Toast.LENGTH_LONG).show();
                }
            });
    }
}















