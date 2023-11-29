package com.example.recipe_app.model;

public class NguyenLieu {
    private int maNguyenLieu;
    private String tenNguyenLieu;
    private int soLuong;
    private int soLuongGoc;
    private String donVi;

    public NguyenLieu() {}

    public NguyenLieu(String tenNguyenLieu, int soLuong, String donVi) {
        this.tenNguyenLieu = tenNguyenLieu;
        this.soLuong = soLuong;
        this.donVi = donVi;
        this.soLuongGoc = soLuong;
    }

    public NguyenLieu(String tenNguyenLieu, int soLuong) {
        this.tenNguyenLieu = tenNguyenLieu;
        this.soLuong = soLuong;
    }

    public NguyenLieu(int maNguyenLieu, String tenNguyenLieu, int soLuong, String donVi) {
        this.maNguyenLieu = maNguyenLieu;
        this.tenNguyenLieu = tenNguyenLieu;
        this.soLuong = soLuong;
        this.donVi = donVi;
    }

    public int getSoLuongGoc() {
        return soLuongGoc;
    }

    public int getMaNguyenLieu() {
        return maNguyenLieu;
    }

    public void setMaNguyenLieu(int maNguyenLieu) {
        this.maNguyenLieu = maNguyenLieu;
    }

    public String getTenNguyenLieu() {
        return tenNguyenLieu;
    }

    public void setTenNguyenLieu(String tenNguyenLieu) {
        this.tenNguyenLieu = tenNguyenLieu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }
}
