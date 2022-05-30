package com.UDEC.educaplay;

public class Contenido {
    private int id;
    private String titulo;
    private String texto;
    private String img;

    public Contenido(int id, String titulo, String texto, String img) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
