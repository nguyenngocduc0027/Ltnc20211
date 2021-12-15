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
import com.example.ltnc20211.quanly.model.MonHoc;
import com.example.ltnc20211.quanly.qlmh.EditMHActivity;
import com.example.ltnc20211.quanly.qlsv.EditSVActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class MonHocAdapter extends ArrayAdapter<MonHoc> {

    private Activity activity;
    private int resource;
    private List<MonHoc> objects;


    public MonHocAdapter(@NonNull Activity activity, int resource, @NonNull List<MonHoc> objects) {
        super(activity, resource, objects);
        this.activity = activity;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View view = inflater.inflate(this.resource,null);

        // khai báo ánh xạ textview của từng thành phần
        TextView tv_maHP = view.findViewById(R.id.text_view_mahp);
        TextView tv_tenHP = view.findViewById(R.id.text_view_tenhp);
        TextView tv_tc = view.findViewById(R.id.text_view_tc);

        // lấy dữ liệu của đối tượng sinh viên đưa lên các textview thành phần
        MonHoc monHoc = this.objects.get(position);
        tv_maHP.setText(monHoc.getMaHP());
        tv_tenHP.setText(monHoc.getTenHP());
        tv_tc.setText(monHoc.getTc());

        ImageView btn_popup_menu_mh = view.findViewById(R.id.btn_icon_menu_mh);
        btn_popup_menu_mh.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ViewHolder")
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu_mh = new PopupMenu(activity,v);
                popupMenu_mh.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item_mh) {
                        if (item_mh.getItemId() == R.id.item_sua_mh){
                            Intent go_to_layout_edit_mh = new Intent(activity, EditMHActivity.class);
                            go_to_layout_edit_mh.putExtra("MONHOC",monHoc);
                            activity.startActivity(go_to_layout_edit_mh);
                            Toast.makeText(getContext(),"Clicked Edit", Toast.LENGTH_LONG).show();
                        }
                        if (item_mh.getItemId() == R.id.item_xoa_mh){
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = database.getReference();
                            databaseReference.child("MonHoc").child(monHoc.getMaHP()).removeValue();
                            Toast.makeText(getContext(),"Clicked Delete", Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }
                });

                // gọi popup_menu.xml vào để show
                popupMenu_mh.getMenuInflater().inflate(R.menu.popup_menu_mh,popupMenu_mh.getMenu());
                // hiển thị icon
                try {
                    Field field = popupMenu_mh.getClass().getDeclaredField("mPopup");
                    field.setAccessible(true);
                    Object popUpMenuHelper = field.get(popupMenu_mh);
                    Class<?> cls = Class.forName("com.android.internal.view.menu.MenuPopupHelper");
                    Method method = cls.getDeclaredMethod("setForceShowIcon", new Class[] {boolean.class});
                    method.setAccessible(true);
                    method.invoke(popUpMenuHelper,new Object[]{true});

                } catch (Exception e){
                    Log.d("MYTAG","OnClick: " + e.toString());
                }
                popupMenu_mh.show();
            }
        });
        return view;
    }
}
