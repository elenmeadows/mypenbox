package com.mypenbox.mpb.models;

import javax.persistence.*;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "category")
    private String category;
    @Column(name = "brand")
    private String brand;
    @Column(name = "type")
    private String type;
    @Column(name = "colormark")
    private String colormark;
    @Column(name = "colorswatch")
    private String colorswatch;
    @Column(name = "colorname")
    private String colorname;
    @Column(name = "img")
    private String img;

    protected Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColormark() {
        return colormark;
    }

    public void setColormark(String colormark) {
        this.colormark = colormark;
    }

    public String getColorswatch() {
        return colorswatch;
    }

    public void setColorswatch(String colorswatch) {
        this.colorswatch = colorswatch;
    }

    public String getColorname() {
        return colorname;
    }

    public void setColorname(String colorname) {
        this.colorname = colorname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
