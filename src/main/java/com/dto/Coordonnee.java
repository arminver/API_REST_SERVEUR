package com.dto;

public class Coordonnee {

    private String abscisse;
    private String ordonnee;

    public Coordonnee(String abscisse, String ordonnee) {
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }

    public String getAbscisse() {
        return abscisse;
    }
    public void setAbscisse(String abscisse) {
        this.abscisse = abscisse;
    }
    public String getOrdonnee() {
        return ordonnee;
    }
    public void setOrdonnee(String ordonnee) {
        this.ordonnee = ordonnee;
    }

}
