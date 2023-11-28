package com.example.recipe_app.model;

public class Video {
    private int maVideo;
    private String tieuDe;
    private String duongDanVideo;

    public Video() {}

    public Video(int maVideo, String tieuDe, String duongDanVideo) {
        this.maVideo = maVideo;
        this.tieuDe = tieuDe;
        this.duongDanVideo = duongDanVideo;
    }

    public int getMaVideo() {
        return maVideo;
    }

    public void setMaVideo(int maVideo) {
        this.maVideo = maVideo;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getDuongDanVideo() {
        return duongDanVideo;
    }

    public void setDuongDanVideo(String duongDanVideo) {
        this.duongDanVideo = duongDanVideo;
    }
}
