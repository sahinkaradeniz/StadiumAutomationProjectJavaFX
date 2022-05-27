package com.example.start_2;



public class maclar {
    private String evsahibi;
    private String konuk;
    private String tarih;
    private String yer;
    private String idmaclar;

    public maclar(String idmaclar, String evsahibi, String konuk, String tarih, String yer) {
        this.idmaclar = idmaclar;
        this.evsahibi = evsahibi;
        this.konuk = konuk;
        this.tarih = tarih;
        this.yer = yer;
    }
    public maclar(){}
    public String getEvsahibi() {
        return this.evsahibi;
    }

    public void setEvsahibi(String evsahibi) {
        this.evsahibi = evsahibi;
    }

    public String getKonuk() {
        return this.konuk;
    }

    public void setKonuk(String konuk) {
        this.konuk = konuk;
    }

    public String getTarih() {
        return this.tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getYer() {
        return this.yer;
    }

    public void setYer(String yer) {
        this.yer = yer;
    }

    public String getIdmaclar() {
        return this.idmaclar;
    }

    public void setId(String id) {
        this.idmaclar = this.idmaclar;
    }
}
