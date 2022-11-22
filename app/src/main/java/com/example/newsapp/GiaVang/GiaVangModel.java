package com.example.newsapp.GiaVang;

public class GiaVangModel {
    String tgian,loaivang,giamua,giaban;

    public GiaVangModel(String tgian, String loaivang, String giamua, String giaban) {
        this.tgian = tgian;
        this.loaivang = loaivang;
        this.giamua = giamua;
        this.giaban = giaban;
    }

    public String getTgian() {
        return tgian;
    }

    public void setTgian(String tgian) {
        this.tgian = tgian;
    }

    public String getLoaivang() {
        return loaivang;
    }

    public void setLoaivang(String loaivang) {
        this.loaivang = loaivang;
    }

    public String getGiamua() {
        return giamua;
    }

    public void setGiamua(String giamua) {
        this.giamua = giamua;
    }

    public String getGiaban() {
        return giaban;
    }

    public void setGiaban(String giaban) {
        this.giaban = giaban;
    }
}
