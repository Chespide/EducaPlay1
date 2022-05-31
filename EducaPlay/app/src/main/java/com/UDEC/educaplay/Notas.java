package com.UDEC.educaplay;

public class Notas {
    private String cant, correctas, incorrectas, puntaje, nivel;

    public Notas(String cant, String correctas, String incorrectas, String puntaje, String nivel) {
        this.cant = cant;
        this.correctas = correctas;
        this.incorrectas = incorrectas;
        this.puntaje = puntaje;
        this.nivel = nivel;
    }

    public String getCant() {
        return cant;
    }

    public void setCant(String cant) {
        this.cant = cant;
    }

    public String getCorrectas() {
        return correctas;
    }

    public void setCorrectas(String correctas) {
        this.correctas = correctas;
    }

    public String getIncorrectas() {
        return incorrectas;
    }

    public void setIncorrectas(String incorrectas) {
        this.incorrectas = incorrectas;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
