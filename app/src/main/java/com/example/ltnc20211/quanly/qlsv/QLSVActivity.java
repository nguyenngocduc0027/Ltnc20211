package com.example.ltnc20211.quanly.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.ltnc20211.R;
import com.example.ltnc20211.quanly.adapter.SinhVienAdapter;
import com.example.ltnc20211.quanly.model.SinhVien;

import java.util.ArrayList;

public class QLSVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsvactivity);


        ListView list_sv = findViewById(R.id.list_sv);


        // tạo danh sách sinh viên
        ArrayList<SinhVien> sinhVienArrayList = new ArrayList<>();
        sinhVienArrayList.add(new SinhVien("20170027","NguyenNgocDuc","16/05/1999","ngocduc.bk.hust@gmail.com","0378049798","Thanh Hoa","1234567"));
        sinhVienArrayList.add(new SinhVien("20170028","NguyenNgocDuc","16/05/1999","ngocduc.bk.hust@gmail.com","0378049798","Thanh Hoa","1234567"));
        sinhVienArrayList.add(new SinhVien("20170029","NguyenNgocDuc","16/05/1999","ngocduc.bk.hust@gmail.com","0378049798","Thanh Hoa","1234567"));
        sinhVienArrayList.add(new SinhVien("20170030","NguyenNgocDuc","16/05/1999","ngocduc.bk.hust@gmail.com","0378049798","Thanh Hoa","1234567"));
        sinhVienArrayList.add(new SinhVien("20170031","NguyenNgocDuc","16/05/1999","ngocduc.bk.hust@gmail.com","0378049798","Thanh Hoa","1234567"));


        // gọi custom adapter để gán cho listview
        //this là layout này, custom_listview_item là layout của từng sinh viên
        // sinhvien_array_list là list data sinh viên được trả về từ firebase
        SinhVienAdapter adapter = new SinhVienAdapter(this,R.layout.custom_listview_item,sinhVienArrayList);


        // tạo adapter cho listview
        list_sv.setAdapter(adapter);
    }
}