package com.example.start_2;

public class fisktür {
    String idfisktür,evsahibi,konuk,tarih;
    fisktür(){}

    public fisktür(String idfisktür, String evsahibi, String konuk, String tarih) {
        this.idfisktür = idfisktür;
        this.evsahibi = evsahibi;
        this.konuk = konuk;
        this.tarih = tarih;
    }

    public String getIdfisktür() {
        return idfisktür;
    }

    public void setIdfisktür(String idfisktür) {
        this.idfisktür = idfisktür;
    }

    public String getEvsahibi() {
        return evsahibi;
    }

    public void setEvsahibi(String evsahibi) {
        this.evsahibi = evsahibi;
    }

    public String getKonuk() {
        return konuk;
    }

    public void setKonuk(String konuk) {
        this.konuk = konuk;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
