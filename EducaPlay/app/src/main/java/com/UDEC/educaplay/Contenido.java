package com.UDEC.educaplay;

import androidx.annotation.NonNull;

public class Contenido {

    private String id, titulo, texto, Contenido, nivel;


    public Contenido(String id, String titulo, String texto,String contenido,String nivel){
        this.id = id;
        this.Contenido = contenido;
        this.nivel = nivel;
        this.titulo = titulo;
        this.texto = texto;

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String texto) {
        this.Contenido = Contenido;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String texto) {
        this.nivel = nivel;
    }

}
