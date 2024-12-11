package com.example.a1211_oraimunka;

public class Item {

  private int id;
  private String name;
  private int mennyiseg;
  private int darab_ar;
  private String kategoria;

  public Item(int id, String name, int mennyiseg, int darab_ar, String kategoria) {
    this.id = id;
    this.name = name;
    this.mennyiseg = mennyiseg;
    this.darab_ar = darab_ar;
    this.kategoria = kategoria;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getMennyiseg() {
    return mennyiseg;
  }

  public void setMennyiseg(int mennyiseg) {
    this.mennyiseg = mennyiseg;
  }

  public int getDarab_ar() {
    return darab_ar;
  }

  public void setDarab_ar(int darab_ar) {
    this.darab_ar = darab_ar;
  }

  public String getKategoria() {
    return kategoria;
  }

  public void setKategoria(String kategoria) {
    this.kategoria = kategoria;
  }
}
