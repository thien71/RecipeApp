package com.example.recipe_app.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BinhLuan {
    private int maBinhLuan;
    private int maNguoiDung;
    private int maCongThuc;
    private String noiDungBinhLuan;
    private String ngayTao;
    private String ngayCapNhat;
    private int soLuongLike;

    public BinhLuan() {
    }

    public BinhLuan(int maNguoiDung, int maCongThuc, int maBinhLuan, String noiDungBinhLuan, int soLuongLike) {
        this.maNguoiDung = maNguoiDung;
        this.maCongThuc = maCongThuc;
        this.maBinhLuan = maBinhLuan;
        this.noiDungBinhLuan = noiDungBinhLuan;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.ngayTao = sdf.format(new Date());
        this.ngayCapNhat = sdf.format(new Date());
        this.soLuongLike = soLuongLike;
    }

    public int getMaBinhLuan() {
        return maBinhLuan;
    }

    public void setMaBinhLuan(int maBinhLuan) {
        this.maBinhLuan = maBinhLuan;
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

    public String getNoiDungBinhLuan() {
        return noiDungBinhLuan;
    }

    public void setNoiDungBinhLuan(String noiDungBinhLuan) {
        this.noiDungBinhLuan = noiDungBinhLuan;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.ngayCapNhat = sdf.format(new Date());
    }

    public int getSoLuongLike() {
        return soLuongLike;
    }

    public void setSoLuongLike(int soLuongLike) {
        this.soLuongLike = soLuongLike;
    }
}
