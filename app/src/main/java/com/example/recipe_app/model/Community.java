package com.example.recipe_app.model;

public class Community {
    private String hinh;
    private int avatar;
    private String tenNguoi;
    private String tenMon;
    private String moTa;
    private int soBinhLuan;
    private int soLike;
    private int isLiked;
    private String textIsSaved;
    private int hinhIsSaved;

    public Community(String hinh, String tenMon) {
        this.hinh = hinh;
        this.tenMon = tenMon;
    }

    public Community(String hinh, int avatar, String tenNguoi, String tenMon, String moTa, int soBinhLuan,
                     int soLike, int isLiked, String textIsSaved, int hinhIsSaved) {
        this.hinh = hinh;
        this.avatar = avatar;
        this.tenNguoi = tenNguoi;
        this.tenMon = tenMon;
        this.moTa = moTa;
        this.soBinhLuan = soBinhLuan;
        this.soLike = soLike;
        this.isLiked = isLiked;
        this.textIsSaved = textIsSaved;
        this.hinhIsSaved = hinhIsSaved;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getTenNguoi() {
        return tenNguoi;
    }

    public void setTenNguoi(String tenNguoi) {
        this.tenNguoi = tenNguoi;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLike() {
        return soLike;
    }

    public void setSoLike(int soLike) {
        this.soLike = soLike;
    }

    public int getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }

    public int getSoBinhLuan() {
        return soBinhLuan;
    }

    public void setSoBinhLuan(int soBinhLuan) {
        this.soBinhLuan = soBinhLuan;
    }

    public String getTextIsSaved() {
        return textIsSaved;
    }

    public void setTextIsSaved(String textIsSaved) {
        this.textIsSaved = textIsSaved;
    }

    public int getHinhIsSaved() {
        return hinhIsSaved;
    }

    public void setHinhIsSaved(int hinhIsSaved) {
        this.hinhIsSaved = hinhIsSaved;
    }
}