package com.example.recipe_app;

public class Cookbooks {
    private int hinh;
    private String ten;
    private int soLuong;

    public Cookbooks(int hinh, String ten, int soLuong) {
        this.hinh = hinh;
        this.ten = ten;
        this.soLuong = soLuong;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
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
