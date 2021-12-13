package com.example.ltnc20211.quanly.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ltnc20211.R;
import com.example.ltnc20211.quanly.model.SinhVien;

import java.util.List;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {

    private Activity activity;
    private int resource;
    private List<SinhVien> objects;


    public SinhVienAdapter(@NonNull Activity activity, int resource, @NonNull List<SinhVien> objects) {
        super(activity, resource, objects);
        this.activity =activity;
        this.resource = resource;
        this.objects = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // mỗi một đới tượng sinh viên sẽ trả về 1 view trong list view
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View view = inflater.inflate(this.resource,null);


        // khai báo ánh xạ textview của từng thành phần của sinh viên
        TextView tv_mssv = view.findViewById(R.id.text_view_mssv);
        TextView tv_name = view.findViewById(R.id.text_view_name);
        TextView tv_dob = view.findViewById(R.id.text_view_dob);
        TextView tv_email = view.findViewById(R.id.text_view_email);
        TextView tv_sdt = view.findViewById(R.id.text_view_sdt);
        TextView tv_dc = view.findViewById(R.id.text_view_dc);

        // lấy dữ liệu của đối tượng sinh viên đưa lên các textview thành phần
        SinhVien sinhVien = this.objects.get(position);
        tv_mssv.setText(sinhVien.getMssv());
        tv_name.setText(sinhVien.getName());
        tv_dob.setText(sinhVien.getDob());
        tv_email.setText(sinhVien.getEmail());
        tv_sdt.setText(sinhVien.getSdt());
        tv_dc.setText(sinhVien.getDc());
        return view;
    }
}
