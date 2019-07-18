package com.example.mpr.models;

//model para las etiquetas
public class Etiqueta {

  //atributos Etiqueta
  private String id;
  private String nombre;
  private String imgUrl;

  //constructor de cada evento
  public Etiqueta(String id, String nombre, String imgUrl) {
    this.id = id;
    this.nombre = nombre;
    this.imgUrl = imgUrl;
  }

  //getter y setters evento
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }
}
