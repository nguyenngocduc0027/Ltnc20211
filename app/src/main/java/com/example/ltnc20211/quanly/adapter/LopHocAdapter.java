package com.example.ltnc20211.quanly.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;

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
import com.example.ltnc20211.quanly.model.LopHoc;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class LopHocAdapter extends ArrayAdapter<LopHoc> {

    private Activity activity;
    private int resource;
    private List<LopHoc> objects;


    public LopHocAdapter(@NonNull Activity activity, int resource, @NonNull List<LopHoc> objects) {
        super(activity, resource, objects);
        this.activity = activity;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // mỗi một đới tượng lớp học sẽ trả về 1 view trong list view
        LayoutInflater inflater = this.activity.getLayoutInflater();
        @SuppressLint("ViewHolder") View view = inflater.inflate(this.resource,null);

        // khai báo ánh xạ textview của từng thành phần
        TextView tv_maLH = view.findViewById(R.id.text_view_maLH);
        TextView tv_tenHP_lh = view.findViewById(R.id.text_view_tenHP_lh);
        TextView tv_tenGV_lh = view.findViewById(R.id.text_view_tenGV_lh);
        TextView tv_soPH_lh = view.findViewById(R.id.text_view_soPH);
        TextView tv_slSV_lh = view.findViewById(R.id.text_view_slSV);
        TextView tv_tgBatDau = view.findViewById(R.id.text_view_tgBatDau);
        TextView tv_tgKetThuc = view.findViewById(R.id.text_view_tgKetThuc);

        // lấy dữ liệu của đối tượng lớp học đưa lên các textview thành phần
        LopHoc lopHoc = this.objects.get(position);
        tv_maLH.setText(lopHoc.getMaLH());
        tv_tenHP_lh.setText(lopHoc.getTenHP());
        tv_tenGV_lh.setText(lopHoc.getTenGV());
        tv_soPH_lh.setText(lopHoc.getSoPH());
        tv_slSV_lh.setText(lopHoc.getSlSV());
        tv_tgBatDau.setText(lopHoc.getTgBatDau());
        tv_tgKetThuc.setText(lopHoc.getTgKetThuc());


        ImageView btn_popup_menu_lh = view.findViewById(R.id.btn_icon_menu_lh);
        btn_popup_menu_lh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(activity, v);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item_lh) {
                        if (item_lh.getItemId() == R.id.item_sua_lh) {
                            Toast.makeText(getContext(), "Clicked Edit", Toast.LENGTH_LONG).show();
                        }
                        if (item_lh.getItemId() ==  R.id.item_xoa_lh) {
                            Toast.makeText(getContext(),"Clicked Delete",Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }
                });
                // gọi popup_menu.xml vào để show
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_lh,popupMenu.getMenu());
                // hiển thị Icon
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
