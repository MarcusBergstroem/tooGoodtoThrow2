package com.example.TooGoodToThrow.Model;

public class Madvare {
    private int id;
    private String madnavn;
    private double pris;
    private int antal;
    private String udlobsdato;
    private String adresse;
    private String virksomhed;

    public Madvare(){
    }

    public Madvare(int id, String madnavn, double pris, int antal, String udlobsdato, String adresse, String virksomhed) {
        this.id = id;
        this.madnavn = madnavn;
        this.pris = pris;
        this.antal = antal;
        this.udlobsdato = udlobsdato;
        this.adresse = adresse;
        this.virksomhed = virksomhed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMadnavn() {
        return madnavn;
    }

    public void setMadnavn(String madnavn) {
        this.madnavn = madnavn;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public String getUdlobsdato() {
        return udlobsdato;
    }

    public void setUdlobsdato(String udlobsdato) {
        this.udlobsdato = udlobsdato;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVirksomhed() {
        return virksomhed;
    }

    public void setVirksomhed(String virksomhed) {
        this.virksomhed = virksomhed;
    }
}
