package com.example.ltnc20211.quanly.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ltnc20211.R;
import com.example.ltnc20211.quanly.model.SinhVien;
import com.example.ltnc20211.quanly.qlsv.AddSVActivity;
import com.example.ltnc20211.quanly.qlsv.EditSVActivity;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
        @SuppressLint("ViewHolder") View view = inflater.inflate(this.resource,null);


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


        ImageView btn_popup_menu = view.findViewById(R.id.btn_icon_menu);
        btn_popup_menu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ViewHolder")
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(activity,v);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.item_them_sv){
                            Intent go_to_layout_add_sv = new Intent(activity, AddSVActivity.class);
                            activity.startActivity(go_to_layout_add_sv);
                            Toast.makeText(getContext(),"Clicked Add", Toast.LENGTH_LONG).show();
                        }
                        if (item.getItemId() == R.id.item_sua_sv){
                            Intent go_to_layout_edit_sv = new Intent(activity, EditSVActivity.class);
                            go_to_layout_edit_sv.putExtra("SINHVIEN",sinhVien);
                            activity.startActivity(go_to_layout_edit_sv);
                            Toast.makeText(getContext(),"Clicked Edit", Toast.LENGTH_LONG).show();
                        }
                        if (item.getItemId() == R.id.item_xoa_sv){
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = database.getReference();
                            databaseReference.child("SinhVien").child("users").child(sinhVien.getMssv()).removeValue();

                        }
                        return false;
                    }
                });

                // gọi popup_menu.xml vào để show
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
                // hiển thị icon
                try {
                    Field field = popupMenu.getClass().getDeclaredField("mPopup");
                    field.setAccessible(true);
                    Object popUpMenuHelper = field.get(popupMenu);
                    Class<?> cls = Class.forName("com.android.internal.view.menu.MenuPopupHelper");
                    Method method = cls.getDeclaredMethod("setForceShowIcon", new Class[] {boolean.class});
                    method.setAccessible(true);
                    method.invoke(popUpMenuHelper,new Object[]{true});

                } catch (Exception e){
                    Log.d("MYTAG","OnClick: " + e.toString());
                }
                popupMenu.show();
            }
        });
        return view;
    }
}
