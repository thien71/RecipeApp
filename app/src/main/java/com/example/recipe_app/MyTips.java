package com.example.recipe_app;

public class MyTips {
    private String content;
    private int likeNumber;
    private String ten;
    private String time;
    private int hinh;

    public MyTips(String content, int likeNumber, String ten, String time) {
        this.content = content;
        this.likeNumber = likeNumber;
        this.ten = ten;
        this.time = time;
    }

    public MyTips(String content, int likeNumber, String ten, String time, int hinh) {
        this.content = content;
        this.likeNumber = likeNumber;
        this.ten = ten;
        this.time = time;
        this.hinh = hinh;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
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

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}
