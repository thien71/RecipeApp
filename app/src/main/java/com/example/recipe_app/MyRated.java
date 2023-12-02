package com.example.recipe_app;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyRated {
    private String hinh;
    private String ten;
    private String ngayDanhGia;
    private int likeOrDislike;

    public MyRated(String hinh, String ten) {
        this.hinh = hinh;
        this.ten = ten;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.ngayDanhGia = sdf.format(new Date());
    }

    public MyRated(String hinh, String ten, String time, int likeOrDislike) {
        this.hinh = hinh;
        this.ten = ten;
        this.ngayDanhGia = time;
        this.likeOrDislike = likeOrDislike;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgayDanhGia() {
        return ngayDanhGia;
    }

    public void setNgayDanhGia(String ngayDanhGia) {
        this.ngayDanhGia = ngayDanhGia;
    }

    public int getLikeOrDislike() {
        return likeOrDislike;
    }

    public void setLikeOrDislike(int likeOrDislike) {
        this.likeOrDislike = likeOrDislike;
    }
}
