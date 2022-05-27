package com.example.start_2;

import com.example.start_2.homePageController.*;
public class User  {
    private int id,bakiye;
    public String tcno,ad,soyad,tel,emal,sifre,bilet,koltuk;

    public User(){

    }
    public User(int id, String tcno, String ad, String soyad, String tel, String emal, String sifre,String bilet,int bakiye,String koltuk) {
        this.id = id;
        this.tcno = tcno;
        this.ad = ad;
        this.soyad = soyad;
        this.tel = tel;
        this.emal = emal;
        this.sifre = sifre;
        this.bilet=bilet;
        this.bakiye = bakiye;
        this.koltuk=koltuk;
    }

    public String getBilet() {
        return bilet;
    }

    public void setBilet(String bilet) {
        this.bilet = bilet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTcno() {
        return tcno;
    }

    public void setTcno(String tcno) {
        this.tcno = tcno;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmal() {
        return emal;
    }

    public String getKoltuk() {
        return koltuk;
    }

    public void setKoltuk(String koltuk) {
        this.koltuk = koltuk;
    }

    public void setEmal(String emal) {
        this.emal = emal;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public int getBakiye() {
        return bakiye;
    }

    public void setBakiye(int bakiye) {
        this.bakiye = bakiye;
    }



}

