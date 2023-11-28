package com.example.recipe_app.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CongThuc {
    private int maCongThuc;
    private int maNguoiDung;
    private String tieuDe;
    private String moTa;
    private String duongDanHinhAnh;
    private String ngayTao;
    private String ngayCapNhat;
    private int maDanhMucCongThuc;
    private List<NguyenLieu> nguyenLieu;
    private List<BuocThucHien> buocThucHien;
    private Video video;
    private ThongTinDinhDuong thongTinDinhDuong;

    public CongThuc() {}

    public CongThuc(int maCongThuc, int maNguoiDung, String tieuDe, String moTa, String duongDanHinhAnh, int maDanhMucCongThuc, List<NguyenLieu> nguyenLieu, List<BuocThucHien> buocThucHien, Video video, ThongTinDinhDuong thongTinDinhDuong) {
        this.maCongThuc = maCongThuc;
        this.maNguoiDung = maNguoiDung;
        this.tieuDe = tieuDe;
        this.moTa = moTa;
        this.duongDanHinhAnh = duongDanHinhAnh;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.ngayTao = sdf.format(new Date());
        this.ngayCapNhat = sdf.format(new Date());

        this.maDanhMucCongThuc = maDanhMucCongThuc;
        this.nguyenLieu = nguyenLieu;
        this.buocThucHien = buocThucHien;
        this.video = video;
        this.thongTinDinhDuong = thongTinDinhDuong;
    }

    public int getMaCongThuc() {
        return maCongThuc;
    }

    public void setMaCongThuc(int maCongThuc) {
        this.maCongThuc = maCongThuc;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getDuongDanHinhAnh() {
        return duongDanHinhAnh;
    }

    public void setDuongDanHinhAnh(String duongDanHinhAnh) {
        this.duongDanHinhAnh = duongDanHinhAnh;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public int getMaDanhMucCongThuc() {
        return maDanhMucCongThuc;
    }

    public void setMaDanhMucCongThuc(int maDanhMucCongThuc) {
        this.maDanhMucCongThuc = maDanhMucCongThuc;
    }

    public List<NguyenLieu> getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(List<NguyenLieu> nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }

    public List<BuocThucHien> getBuocThucHien() {
        return buocThucHien;
    }

    public void setBuocThucHien(List<BuocThucHien> buocThucHien) {
        this.buocThucHien = buocThucHien;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public ThongTinDinhDuong getThongTinDinhDuong() {
        return thongTinDinhDuong;
    }

    public void setThongTinDinhDuong(ThongTinDinhDuong thongTinDinhDuong) {
        this.thongTinDinhDuong = thongTinDinhDuong;
    }

    public void capNhatNgayCapNhat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.ngayCapNhat = sdf.format(new Date());
    }
}
