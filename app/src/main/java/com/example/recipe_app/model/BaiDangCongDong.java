package com.example.recipe_app.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
public class BaiDangCongDong implements Serializable {
    private int maNguoiDung;
    private int maBaiDang;
    private String tieuDe;
    private String noiDung;
    private String hinhAnh;
    private String ngayDang;
    private int soLike;
    private int soBinhLuan;
    private NguoiDung nguoiDung;
    private boolean isLiked = false;

    public BaiDangCongDong() {
    }

    public BaiDangCongDong(int maNguoiDung, int maBaiDang, String tieuDe, String noiDung, String hinhAnh, int soLike, int soBinhLuan, NguoiDung nguoiDung, boolean isLiked) {
        this.maNguoiDung = maNguoiDung;
        this.maBaiDang = maBaiDang;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.hinhAnh = hinhAnh;
        this.soLike = soLike;
        this.soBinhLuan = soBinhLuan;
        this.nguoiDung = nguoiDung;
        this.isLiked = isLiked;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.ngayDang = sdf.format(new Date());
    }

    public BaiDangCongDong(int maNguoiDung, int maBaiDang, String tieuDe, String noiDung, String hinhAnh, int soLike, int soBinhLuan, NguoiDung nguoiDung) {
        this.maNguoiDung = maNguoiDung;
        this.maBaiDang = maBaiDang;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.hinhAnh = hinhAnh;
        this.soLike = soLike;
        this.soBinhLuan = soBinhLuan;
        this.nguoiDung = nguoiDung;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.ngayDang = sdf.format(new Date());
    }

    public BaiDangCongDong(int maNguoiDung, int maBaiDang, String tieuDe, String noiDung, String hinhAnh, int soLike, int soBinhLuan) {
        this.maNguoiDung = maNguoiDung;
        this.maBaiDang = maBaiDang;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.hinhAnh = hinhAnh;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.ngayDang = sdf.format(new Date());
        this.soLike = soLike;
        this.soBinhLuan = soBinhLuan;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public int getSoBinhLuan() {
        return soBinhLuan;
    }

    public void setSoBinhLuan(int soBinhLuan) {
        this.soBinhLuan = soBinhLuan;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public int getMaBaiDang() {
        return maBaiDang;
    }

    public void setMaBaiDang(int maBaiDang) {
        this.maBaiDang = maBaiDang;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public int getSoLike() {
        return soLike;
    }

    public void setSoLike(int soLike) {
        this.soLike = soLike;
    }
}
