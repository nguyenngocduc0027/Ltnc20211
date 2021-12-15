package com.example.ltnc20211.quanly.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class MonHoc implements Serializable {
    private String maHP, tenHP, tc;

    @Override
    public String toString() {
        return "MonHoc{" +
                "maHP='" + maHP + '\'' +
                ", tenHP='" + tenHP + '\'' +
                ", tc='" + tc + '\'' +
                '}';
    }

    public MonHoc() {
    }

    public MonHoc(String maHP, String tenHP, String tc) {
        this.maHP = maHP;
        this.tenHP = tenHP;
        this.tc = tc;
    }

    public String getMaHP() {
        return maHP;
    }

    public void setMaHP(String maHP) {
        this.maHP = maHP;
    }

    public String getTenHP() {
        return tenHP;
    }

    public void setTenHP(String tenHP) {
        this.tenHP = tenHP;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }
}
