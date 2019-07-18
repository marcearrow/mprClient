package com.example.mpr.models;

public class Producto {
    String id;
    String imgUrl;
    String nombre;


    public Producto(String id, String imgUrl, String nombre) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
