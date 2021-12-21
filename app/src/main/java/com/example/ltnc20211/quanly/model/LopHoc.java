package com.example.ltnc20211.quanly.model;

import java.io.Serializable;

public class LopHoc implements Serializable {

    String maLH, tenHP, tenGV, soPH, slSV, tgBatDau, tgKetThuc;

    @Override
    public String toString() {
        return "LopHoc{" +
                "maLH='" + maLH + '\'' +
                ", tenHP='" + tenHP + '\'' +
                ", tenGV='" + tenGV + '\'' +
                ", soPH='" + soPH + '\'' +
                ", slSV='" + slSV + '\'' +
                ", tgBatDau='" + tgBatDau + '\'' +
                ", tgKetThuc='" + tgKetThuc + '\'' +
                '}';
    }

    public LopHoc() {
    }

    public LopHoc(String maLH, String tenHP, String tenGV, String soPH, String slSV, String tgBatDau, String tgKetThuc) {
        this.maLH = maLH;
        this.tenHP = tenHP;
        this.tenGV = tenGV;
        this.soPH = soPH;
        this.slSV = slSV;
        this.tgBatDau = tgBatDau;
        this.tgKetThuc = tgKetThuc;
    }

    public String getMaLH() {
        return maLH;
    }

    public void setMaLH(String maLH) {
        this.maLH = maLH;
    }

    public String getTenHP() {
        return tenHP;
    }

    public void setTenHP(String tenHP) {
        this.tenHP = tenHP;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public String getSoPH() {
        return soPH;
    }

    public void setSoPH(String soPH) {
        this.soPH = soPH;
    }

    public String getSlSV() {
        return slSV;
    }

    public void setSlSV(String slSV) {
        this.slSV = slSV;
    }

    public String getTgBatDau() {
        return tgBatDau;
    }

    public void setTgBatDau(String tgBatDau) {
        this.tgBatDau = tgBatDau;
    }

    public String getTgKetThuc() {
        return tgKetThuc;
    }

    public void setTgKetThuc(String tgKetThuc) {
        this.tgKetThuc = tgKetThuc;
    }
}
