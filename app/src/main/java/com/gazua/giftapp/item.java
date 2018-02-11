package com.gazua.giftapp;

/**
 * Created by jonkim on 2018-02-10.
 */

public class item {
    private String name;
    private float price;
    private String image;

    public item(String name, float price, String image){
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName(){
        return this.name;
    }
    public float getPrice(){
        return this.price;
    }
    public String getImage(){
        return this.image;
    }
}
