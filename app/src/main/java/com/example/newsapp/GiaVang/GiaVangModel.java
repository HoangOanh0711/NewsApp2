package com.example.newsapp.GiaVang;

public class GiaVangModel {
    String hang,tenhang,giatienmua,tangmua,giatienban,tangban;

    public GiaVangModel(String hang, String tenhang, String giatienmua, String tangmua, String giatienban, String tangban) {
        this.hang = hang;
        this.tenhang = tenhang;
        this.giatienmua = giatienmua;
        this.tangmua = tangmua;
        this.giatienban = giatienban;
        this.tangban = tangban;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getTenhang() {
        return tenhang;
    }

    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }

    public String getGiatienmua() {
        return giatienmua;
    }

    public void setGiatienmua(String giatienmua) {
        this.giatienmua = giatienmua;
    }

    public String getTangmua() {
        return tangmua;
    }

    public void setTangmua(String tangmua) {
        this.tangmua = tangmua;
    }

    public String getGiatienban() {
        return giatienban;
    }

    public void setGiatienban(String giatienban) {
        this.giatienban = giatienban;
    }

    public String getTangban() {
        return tangban;
    }

    public void setTangban(String tangban) {
        this.tangban = tangban;
    }
}
