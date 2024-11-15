package com.example.carmate;

public class review {
      private String name,carname,rate,review;
      private String imageButton;

    public review() {
    }

    public review(String name, String carname, String rate, String review, String imageButton) {
        this.name = name;
        this.carname = carname;
        this.rate = rate;
        this.review = review;
        this.imageButton = imageButton;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getImageButton() {
        return imageButton;
    }

    public void setImageButton(String imageButton) {
        this.imageButton = imageButton;
    }
}
