package com.example.ltnc20211.quanly.qlmh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ltnc20211.R;
import com.example.ltnc20211.quanly.adapter.MonHocAdapter;
import com.example.ltnc20211.quanly.model.MonHoc;
import com.example.ltnc20211.quanly.model.SinhVien;
import com.example.ltnc20211.quanly.qlsv.AddSVActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QLMHActivity extends AppCompatActivity {

    private ListView list_mh;
    private ArrayList<MonHoc> monHocArrayList;
    private MonHocAdapter adapter;
    private Button btn_add_mh_big;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlmhactivity);

        list_mh = findViewById(R.id.list_mh);
        btn_add_mh_big = findViewById(R.id.btn_add_mh_big);

        // tạo danh sách sinh viên
        monHocArrayList = new ArrayList<>();
//        monHocArrayList.add(new MonHoc("ET3010","LAP TRINH NANG CAO","3"));
//        monHocArrayList.add(new MonHoc("ET3010","LAP TRINH NANG CAO","3"));
//        monHocArrayList.add(new MonHoc("ET3010","LAP TRINH NANG CAO","3"));
//        monHocArrayList.add(new MonHoc("ET3010","LAP TRINH NANG CAO","3"));
//        monHocArrayList.add(new MonHoc("ET3010","LAP TRINH NANG CAO","3"));
        GetDataMH();

        // tạo adater
        adapter = new MonHocAdapter(this, R.layout.custom_listview_mh_item, monHocArrayList);
        // tạo adapter cho listview
        list_mh.setAdapter(adapter);

        btn_add_mh_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddMHActivity.class);
                startActivity(intent);
            }
        });
    }

    // Lấy danh sách sinh viên từ firebase
    private void GetDataMH() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().child("MonHoc");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // cập nhật lại dữ liệu mới lên list_sv và xóa dữ liệu cũ
                adapter.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // chuyển đổi dữ liệu qua class SinhVien
                    MonHoc monHoc = dataSnapshot.getValue(MonHoc.class);
                    // thêm sinh viên vào list_sv
                    assert monHoc != null;
                    monHoc.setMaHP(dataSnapshot.getKey());
                    adapter.add(monHoc);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Fail" + error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
