package com.example.ltnc20211.quanly.model;

import java.io.Serializable;

public class GiangVien implements Serializable {

    private String maGV, tenGV, gtGV, dobGV, dcGV;


    @Override
    public String toString() {
        return "GiangVien{" +
                "maGV='" + maGV + '\'' +
                ", tenGV='" + tenGV + '\'' +
                ", gtGV='" + gtGV + '\'' +
                ", dobGV='" + dobGV + '\'' +
                ", dcGV='" + dcGV + '\'' +
                '}';
    }

    public GiangVien() {
    }

    public GiangVien(String maGV, String tenGV, String gtGV, String dobGV, String dcGV) {
        this.maGV = maGV;
        this.tenGV = tenGV;
        this.gtGV = gtGV;
        this.dobGV = dobGV;
        this.dcGV = dcGV;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public String getGtGV() {
        return gtGV;
    }

    public void setGtGV(String gtGV) {
        this.gtGV = gtGV;
    }

    public String getDobGV() {
        return dobGV;
    }

    public void setDobGV(String dobGV) {
        this.dobGV = dobGV;
    }

    public String getDcGV() {
        return dcGV;
    }

    public void setDcGV(String dcGV) {
        this.dcGV = dcGV;
    }
}
