package com.mypenbox.mpb.models;

import javax.persistence.*;


@Entity
public class Product {

    private Long id;
    private String category;
    private String brand;
    private String type;
    private String colormark;
    private String colorswatch;
    private String colorname;
    private String img;

    protected Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "colormark")
    public String getColormark() {
        return colormark;
    }

    public void setColormark(String colormark) {
        this.colormark = colormark;
    }

    @Column(name = "colorswatch")
    public String getColorswatch() {
        return colorswatch;
    }

    public void setColorswatch(String colorswatch) {
        this.colorswatch = colorswatch;
    }

    @Column(name = "colorname")
    public String getColorname() {
        return colorname;
    }

    public void setColorname(String colorname) {
        this.colorname = colorname;
    }

    @Column(name = "img")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
