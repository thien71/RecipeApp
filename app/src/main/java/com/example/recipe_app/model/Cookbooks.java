package com.example.recipe_app.model;

public class Cookbooks {
    private String hinh;
    private String ten;
    private int soLuong;
    private int maCongThuc;

    public Cookbooks(String hinh, String ten, int soLuong) {
        this.hinh = hinh;
        this.ten = ten;
        this.soLuong = soLuong;
    }

    public Cookbooks(String hinh, String ten, int soLuong, int maCongThuc) {
        this.hinh = hinh;
        this.ten = ten;
        this.soLuong = soLuong;
        this.maCongThuc = maCongThuc;
    }

    public int getMaCongThuc() {
        return maCongThuc;
    }

    public void setMaCongThuc(int maCongThuc) {
        this.maCongThuc = maCongThuc;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
