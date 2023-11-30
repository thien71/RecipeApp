package com.example.recipe_app.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NguoiDung {
    private int maNguoiDung;
    private String tenNguoiDung;
    private String tenDangNhap;
    private String matKhau;
    private String avatar;
    private String ngayTao;
    private int quyen;
    public NguoiDung() {

    }

    public NguoiDung(String tenNguoiDung, String avatar) {
        this.tenNguoiDung = tenNguoiDung;
        this.avatar = avatar;
    }

    public NguoiDung(int maNguoiDung, String tenDangNhap, String matKhau) {
        this.maNguoiDung = maNguoiDung;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.quyen = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.ngayTao = sdf.format(new Date());
    }

    public NguoiDung(int maNguoiDung, String tenNguoiDung, String tenDangNhap, String matKhau, String avatar, int quyen) {
        this.maNguoiDung = maNguoiDung;
        this.tenNguoiDung = tenNguoiDung;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.avatar = avatar;
        this.quyen = quyen;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.ngayTao = sdf.format(new Date());
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
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

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getQuyen() {
        return quyen;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }
}
