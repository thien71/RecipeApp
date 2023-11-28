package com.example.recipe_app.model;

public class ThongTinDinhDuong {
    private int calo;
    private int chatBeo;
    private int protein;
    private int carbohydrate;

    public ThongTinDinhDuong(){}

    public ThongTinDinhDuong(int calo, int chatBeo, int protein, int carbohydrate) {
        this.calo = calo;
        this.chatBeo = chatBeo;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
    }

    public int getCalo() {
        return calo;
    }

    public void setCalo(int calo) {
        this.calo = calo;
    }

    public int getChatBeo() {
        return chatBeo;
    }

    public void setChatBeo(int chatBeo) {
        this.chatBeo = chatBeo;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }
}
