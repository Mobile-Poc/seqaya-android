package com.ntg.user.sa2aia;

/**
 * Created by islam on 3/26/2018.
 */

public class Product {

    private int id;
    private String name;
    private String manufacturer;
    private int bottleSize;
    private int no_bpp;
    private int price;

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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getBottleSize() {
        return bottleSize;
    }

    public void setBottleSize(int bottleSize) {
        this.bottleSize = bottleSize;
    }

    public int getNo_bpp() {
        return no_bpp;
    }

    public void setNo_bpp(int no_bpp) {
        this.no_bpp = no_bpp;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}