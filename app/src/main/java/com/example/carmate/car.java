package com.example.carmate;

public class car {
     private String thecarname, theeditions, thetype, themanufacturedyear, thebrand, theprice, thefuelconsumption, theseats, thespecifications, thepros, thecons, theservicecost, thecertificates;
     private String thecarimage;

    public car() {
    }

    public car(String carname, String editions, String type, String manufacturedyear, String brand, String price, String fuelconsumption, String seats, String specifications, String pros, String cons, String servicecost, String certificates, String carimage) {
        this.thecarname = carname;
        this.theeditions = editions;
        this.thetype = type;
        this.themanufacturedyear = manufacturedyear;
        this.thebrand = brand;
        this.theprice = price;
        this.thefuelconsumption = fuelconsumption;
        this.theseats = seats;
        this.thespecifications = specifications;
        this.thepros = pros;
        this.thecons = cons;
        this.theservicecost = servicecost;
        this.thecertificates = certificates;
        this.thecarimage = carimage;
    }

    public String getThecarname() {
        return thecarname;
    }

    public void setThecarname(String thecarname) {
        this.thecarname = thecarname;
    }

    public String getTheeditions() {
        return theeditions;
    }

    public void setTheeditions(String theeditions) {
        this.theeditions = theeditions;
    }

    public String getThetype() {
        return thetype;
    }

    public void setThetype(String thetype) {
        this.thetype = thetype;
    }

    public String getThemanufacturedyear() {
        return themanufacturedyear;
    }

    public void setThemanufacturedyear(String themanufacturedyear) {
        this.themanufacturedyear = themanufacturedyear;
    }

    public String getThebrand() {
        return thebrand;
    }

    public void setThebrand(String thebrand) {
        this.thebrand = thebrand;
    }

    public String getTheprice() {
        return theprice;
    }

    public void setTheprice(String theprice) {
        this.theprice = theprice;
    }

    public String getThefuelconsumption() {
        return thefuelconsumption;
    }

    public void setThefuelconsumption(String thefuelconsumption) {
        this.thefuelconsumption = thefuelconsumption;
    }

    public String getTheseats() {
        return theseats;
    }

    public void setTheseats(String theseats) {
        this.theseats = theseats;
    }

    public String getThespecifications() {
        return thespecifications;
    }

    public void setThespecifications(String thespecifications) {
        this.thespecifications = thespecifications;
    }

    public String getThepros() {
        return thepros;
    }

    public void setThepros(String thepros) {
        this.thepros = thepros;
    }

    public String getThecons() {
        return thecons;
    }

    public void setThecons(String thecons) {
        this.thecons = thecons;
    }

    public String getTheservicecost() {
        return theservicecost;
    }

    public void setTheservicecost(String theservicecost) {
        this.theservicecost = theservicecost;
    }

    public String getThecertificates() {
        return thecertificates;
    }

    public void setThecertificates(String thecertificates) {
        this.thecertificates = thecertificates;
    }

    public String getThecarimage() {
        return thecarimage;
    }

    public void setThecarimage(String thecarimage) {
        this.thecarimage = thecarimage;
    }
}
