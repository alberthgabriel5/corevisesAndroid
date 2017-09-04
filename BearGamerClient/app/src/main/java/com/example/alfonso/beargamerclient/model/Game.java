package com.example.alfonso.beargamerclient.model;

/**
 * Created by Alfonso on 3/9/2017.
 */

public class Game {

    private String id;
    private String name;
    private String type;
    private String consol;
    private float price;

    public Game(String id,String name,String type,String consol,float price){
        this.name = name;
        this.consol = consol;
        this.type = type;
        this.price = price;
        this.setId(id);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConsol() {
        return consol;
    }

    public void setConsol(String consol) {
        this.consol = consol;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getId() { return id;}

    public void setId(String id) {this.id = id;}
}
