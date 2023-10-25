package com.example.recipe_app;

public class MyRated {
    private int hinh;
    private String ten;
    private String time;
    private int likeOrDislike;

    public MyRated(int hinh, String ten, String time, int likeOrDislike) {
        this.hinh = hinh;
        this.ten = ten;
        this.time = time;
        this.likeOrDislike = likeOrDislike;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLikeOrDislike() {
        return likeOrDislike;
    }

    public void setLikeOrDislike(int likeOrDislike) {
        this.likeOrDislike = likeOrDislike;
    }
}
