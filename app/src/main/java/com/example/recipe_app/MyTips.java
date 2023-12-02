package com.example.recipe_app;

public class MyTips {
    private String content;
    private String ten;
    private String time;
    private int hinh;



    public MyTips(String content, String ten, String time) {
        this.content = content;
        this.ten = ten;
        this.time = time;
    }

    public MyTips(String content, String ten, String time, int hinh) {
        this.content = content;
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
