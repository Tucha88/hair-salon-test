package hairsaon.models;

import java.io.Serializable;

/**
 * Created by Boris on 09.04.2017.
 */


public class Services implements Serializable {
    private static final long serialVersionUID = 112234558L;


    private String name;
    private int duration; // в минутах
    private int price;
    private String info;
//    private String service;
//    private int price;
//    private int time;



    public Services() {
    }

    public Services(String name, int duration, int price) {
        this.name = name;
        this.duration = duration;
        this.price = price;
    }

    public Services(String name, int duration, int price, String info) {
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
