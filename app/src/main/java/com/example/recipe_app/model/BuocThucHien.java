package com.example.recipe_app.model;

public class BuocThucHien {
    private int maBuoc;
    private String moTa;

    public BuocThucHien() {
    }

    public BuocThucHien(int maBuoc, String moTa) {
        this.maBuoc = maBuoc;
        this.moTa = moTa;
    }

    public int getMaBuoc() {
        return maBuoc;
    }

    public void setMaBuoc(int maBuoc) {
        this.maBuoc = maBuoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}