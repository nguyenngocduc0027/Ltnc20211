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
import com.example.ltnc20211.quanly.model.GiangVien;
import com.example.ltnc20211.quanly.qlgv.EditGVActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class GiangVienAdapter extends ArrayAdapter<GiangVien> {

    private Activity activity;
    private int resource;
    private List<GiangVien> objects;

    public GiangVienAdapter(@NonNull Activity activity, int resource, @NonNull List<GiangVien> objects) {
        super(activity, resource, objects);
        this.activity = activity;
        this.resource = resource;
        this.objects =objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        @SuppressLint("ViewHolder") View view = inflater.inflate(this.resource,null);

        // khai báo ánh xạ textview của từng thành phần
        TextView tv_maGV = view.findViewById(R.id.text_view_maGV);
        TextView tv_tenGV = view.findViewById(R.id.text_view_tenGV);
        TextView tv_gtGV = view.findViewById(R.id.text_view_gtGV);
        TextView tv_dobGV = view.findViewById(R.id.text_view_dobGV);
        TextView tv_dcGV = view.findViewById(R.id.text_view_dcGV);

        // lấy dữ liệu của đối tượng đưa lên các textview thành phần
        GiangVien giangVien = this.objects.get(position);
        tv_maGV.setText(giangVien.getMaGV());
        tv_tenGV.setText(giangVien.getTenGV());
        tv_gtGV.setText(giangVien.getGtGV());
        tv_dobGV.setText(giangVien.getDobGV());
        tv_dcGV.setText(giangVien.getDcGV());


        ImageView btn_popup_menu_gv = view.findViewById(R.id.btn_icon_menu_gv);
        btn_popup_menu_gv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu_gv = new PopupMenu(activity,v);
                popupMenu_gv.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item_gv) {
                        if (item_gv.getItemId() == R.id.item_sua_gv){
                            Intent go_to_layout_edit_gv = new Intent(activity, EditGVActivity.class);
                            go_to_layout_edit_gv.putExtra("GIANGVIEN",giangVien);
                            activity.startActivity(go_to_layout_edit_gv);
                            Toast.makeText(getContext(),"Clicked Edit", Toast.LENGTH_LONG).show();
                        }
                        if (item_gv.getItemId() == R.id.item_xoa_gv){
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = database.getReference();
                            databaseReference.child("GiangVien").child(giangVien.getMaGV()).removeValue();
                            Toast.makeText(getContext(),"Clicked Delete", Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }
                });
                // gọi popup menu để show
// gọi popup_menu.xml vào để show
                popupMenu_gv.getMenuInflater().inflate(R.menu.popup_menu_gv,popupMenu_gv.getMenu());
                // hiển thị icon
                try {
                    Field field = popupMenu_gv.getClass().getDeclaredField("mPopup");
                    field.setAccessible(true);
                    Object popUpMenuHelper = field.get(popupMenu_gv);
                    Class<?> cls = Class.forName("com.android.internal.view.menu.MenuPopupHelper");
                    Method method = cls.getDeclaredMethod("setForceShowIcon", new Class[] {boolean.class});
                    method.setAccessible(true);
                    method.invoke(popUpMenuHelper,new Object[]{true});

                } catch (Exception e){
                    Log.d("MYTAG","OnClick: " + e.toString());
                }
                popupMenu_gv.show();
            }
        });
        return view;
    }
}



























