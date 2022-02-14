package com.mypenbox.mpb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "catalog")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
