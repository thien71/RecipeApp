package com.example.recipe_app.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UaThich {
    private int maNguoiDung;
    private int maCongThuc;
    private int maUaThich;
    private String ngayThem;

    public UaThich() {
    }

    public UaThich(int maCongThuc, int maUaThich) {
        this.maCongThuc = maCongThuc;
        this.maUaThich = maUaThich;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.ngayThem = sdf.format(new Date());
    }

    public int getMaUaThich() {
        return maUaThich;
    }

    public void setMaUaThich(int maUaThich) {
        this.maUaThich = maUaThich;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public int getMaCongThuc() {
        return maCongThuc;
    }

    public void setMaCongThuc(int maCongThuc) {
        this.maCongThuc = maCongThuc;
    }

    public String getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(String ngayThem) {
        this.ngayThem = ngayThem;
    }
}
