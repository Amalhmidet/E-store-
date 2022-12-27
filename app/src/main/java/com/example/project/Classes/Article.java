package com.example.project.Classes;

public class Article {

    private String reference;
    private String libelle;
    private String description;
    private float prix;
    private int image;

    public String getImagedata() {
        return imagedata;
    }

    public void setImagedata(String imagedata) {
        this.imagedata = imagedata;
    }

    private String imagedata;

    public Article(String reference, String libelle, String description, float prix, int image,String imagedata) {
        this.reference = reference;
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.imagedata = imagedata;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
