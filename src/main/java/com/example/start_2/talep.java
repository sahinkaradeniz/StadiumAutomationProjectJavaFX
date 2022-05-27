package com.example.start_2;

public class talep {
    String idtalepler,ad,soyad,talep;
    public talep(){}

    public talep(String idtalepler, String ad, String soyad, String talep) {
        this.idtalepler = idtalepler;
        this.ad = ad;
        this.soyad = soyad;
        this.talep = talep;
    }

    public String getIdtalepler() {
        return idtalepler;
    }

    public void setIdtalepler(String idtalepler) {
        this.idtalepler = idtalepler;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTalep() {
        return talep;
    }

    public void setTalep(String talep) {
        this.talep = talep;
    }
}
