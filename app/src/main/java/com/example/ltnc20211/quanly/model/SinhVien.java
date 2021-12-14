package com.example.ltnc20211.quanly.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private String mssv, name, dob, email, sdt, dc, password;

    @NonNull
    @Override
    public String toString() {
        return "SinhVien{" +
                "mssv='" + mssv + '\'' +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", email='" + email + '\'' +
                ", sdt='" + sdt + '\'' +
                ", dc='" + dc + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public SinhVien() {
    }

    public SinhVien(String mssv, String name, String dob, String email, String sdt, String dc, String password) {
        this.mssv = mssv;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.sdt = sdt;
        this.dc = dc;
        this.password = password;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
