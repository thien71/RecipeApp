package com.example.recipe_app.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BinhLuan {
    private int maNguoiDung;
    private int maBaiDang;
    private int maBinhLuan;
    private String tenNguoiDung;
    private String avatar;
    private String noiDungBinhLuan;
    private String ngayBinhLuan;
    private String ngayCapNhat;

    public BinhLuan() {
    }

    public BinhLuan(int maBaiDang, String noiDungBinhLuan) {
        this.maBaiDang = maBaiDang;
        this.noiDungBinhLuan = noiDungBinhLuan;
    }

    public BinhLuan(String tenNguoiDung, String avatar, String noiDungBinhLuan) {
        this.tenNguoiDung = tenNguoiDung;
        this.avatar = avatar;
        this.noiDungBinhLuan = noiDungBinhLuan;
    }

    public BinhLuan(int maNguoiDung, String tenNguoiDung, String avatar, String noiDungBinhLuan) {
        this.maNguoiDung = maNguoiDung;
        this.tenNguoiDung = tenNguoiDung;
        this.avatar = avatar;
        this.noiDungBinhLuan = noiDungBinhLuan;
    }

    public BinhLuan(int maBaiDang, int maBinhLuan, String noiDungBinhLuan) {
        this.maBaiDang = maBaiDang;
        this.maBinhLuan = maBinhLuan;
        this.noiDungBinhLuan = noiDungBinhLuan;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.ngayBinhLuan = sdf.format(new Date());
        this.ngayCapNhat = sdf.format(new Date());
    }

    public BinhLuan(int maNguoiDung, int maBinhLuan, String tenNguoiDung, String avatar, String noiDungBinhLuan) {
        this.maNguoiDung = maNguoiDung;
        this.maBinhLuan = maBinhLuan;
        this.tenNguoiDung = tenNguoiDung;
        this.avatar = avatar;
        this.noiDungBinhLuan = noiDungBinhLuan;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public int getMaBinhLuan() {
        return maBinhLuan;
    }

    public void setMaBinhLuan(int maBinhLuan) {
        this.maBinhLuan = maBinhLuan;
    }

    public int getMaBaiDang() {
        return maBaiDang;
    }

    public void setMaBaiDang(int maBaiDang) {
        this.maBaiDang = maBaiDang;
    }

    public String getNgayBinhLuan() {
        return ngayBinhLuan;
    }

    public void setNgayBinhLuan(String ngayBinhLuan) {
        this.ngayBinhLuan = ngayBinhLuan;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNoiDungBinhLuan() {
        return noiDungBinhLuan;
    }

    public void setNoiDungBinhLuan(String noiDungBinhLuan) {
        this.noiDungBinhLuan = noiDungBinhLuan;
    }
}
