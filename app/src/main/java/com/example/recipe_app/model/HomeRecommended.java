package com.example.recipe_app.model;

public class HomeRecommended {
    private int maCongThuc;
    private String hinh;
    private String ten;

    public HomeRecommended(int maCongThuc, String hinh, String ten) {
        this.maCongThuc = maCongThuc;
        this.hinh = hinh;
        this.ten = ten;
    }

    public HomeRecommended(String hinh, String ten) {
        this.hinh = hinh;
        this.ten = ten;
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
}
