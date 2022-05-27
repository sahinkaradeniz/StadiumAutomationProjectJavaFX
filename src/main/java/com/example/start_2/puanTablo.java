package com.example.start_2;

public class puanTablo {
    String idp,takim,oynanan,galibiyet,beraber,maglup,a,y,averaj,puan;

    public puanTablo(String idp, String takim, String oynanan, String galibiyet, String beraber, String maglup, String a, String y, String averaj, String puan) {
        this.idp = idp;
        this.takim = takim;
        this.oynanan = oynanan;
        this.galibiyet = galibiyet;
        this.beraber = beraber;
        this.maglup = maglup;
        this.a = a;
        this.y = y;
        this.averaj = averaj;
        this.puan = puan;
    }

    public String getIdp() {
        return idp;
    }

    public void setIdp(String idp) {
        this.idp = idp;
    }

    public String getTakim() {
        return takim;
    }

    public void setTakim(String takim) {
        this.takim = takim;
    }

    public String getOynanan() {
        return oynanan;
    }

    public void setOynanan(String oynanan) {
        this.oynanan = oynanan;
    }

    public String getGalibiyet() {
        return galibiyet;
    }

    public void setGalibiyet(String galbiyet) {
        this.galibiyet = galbiyet;
    }

    public String getBeraber() {
        return beraber;
    }

    public void setBeraber(String beraber) {
        this.beraber = beraber;
    }

    public String getMaglup() {
        return maglup;
    }

    public void setMaglup(String maglup) {
        this.maglup = maglup;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getAveraj() {
        return averaj;
    }

    public void setAveraj(String averaj) {
        this.averaj = averaj;
    }

    public String getPuan() {
        return puan;
    }

    public void setPuan(String puan) {
        this.puan = puan;
    }
}
