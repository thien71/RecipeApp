package com.example.recipe_app.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BinhLuan {
    private String tenNguoiDung;
    private String avatar;
    private String noiDungBinhLuan;

    public BinhLuan() {
    }

    public BinhLuan(String tenNguoiDung, String avatar, String noiDungBinhLuan) {
        this.tenNguoiDung = tenNguoiDung;
        this.avatar = avatar;
        this.noiDungBinhLuan = noiDungBinhLuan;
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
