package com.example.recipe_app.model;

public class DanhMucCongThuc {
    private int maDanhMucCongThuc;
    private String tenDanhMuc;

    public DanhMucCongThuc() {
    }

    public DanhMucCongThuc(int maDanhMuc, String tenDanhMuc) {
        this.maDanhMucCongThuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
    }

    public int getMaDanhMucCongThuc() {
        return maDanhMucCongThuc;
    }

    public void setMaDanhMucCongThuc(int maDanhMucCongThuc) {
        this.maDanhMucCongThuc = maDanhMucCongThuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }
}
