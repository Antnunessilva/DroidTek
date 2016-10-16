package com.aldaircruz.droidtek.model;

import static com.aldaircruz.droidtek.util.Util.randomNumberGenerator;

/**
 * Created by Cruz on 16-10-2016.
 */

public class Product {
    private String name;
    private Double price;
    private int id;
    private int serialNumber;

    private static int counter=0;
    private static Double total=0d;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.id = newProductId();

        this.total+=price;
        this.serialNumber = randomNumberGenerator((counter+1)*2,(counter+1)*4);
    }

    public Product(String name, double price,int position) {
        this.name = name;
        this.price = price;

        this.total+=price;
        this.id = position;
        this.serialNumber = randomNumberGenerator((counter+1)*2,(counter+1)*4);
        counter++;
    }

    public Product() {
        this.serialNumber = randomNumberGenerator((counter+1)*2,(counter+1)*4);
        this.id = newProductId();
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Double getTotalPrice() {
        return total;
    }

    public static void setTotalPrice(Double t) {
        total=t;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
        this.total+=price;
    }

    private static int newProductId(){
        if(counter==0){
            counter=1;
        }
        else{
            counter++;
        }
        return counter;
    }

}
