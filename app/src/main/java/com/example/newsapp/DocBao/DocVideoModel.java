package com.example.newsapp.DocBao;

public class DocVideoModel {
    String video,tieude,tgian,ndung,tacgia;

    public DocVideoModel(String video, String tieude, String tgian, String ndung, String tacgia) {
        this.video = video;
        this.tieude = tieude;
        this.tgian = tgian;
        this.ndung = ndung;
        this.tacgia = tacgia;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getTgian() {
        return tgian;
    }

    public void setTgian(String tgian) {
        this.tgian = tgian;
    }

    public String getNdung() {
        return ndung;
    }

    public void setNdung(String ndung) {
        this.ndung = ndung;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }
}
