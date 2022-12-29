package com.example.driverappandroidclient.model;

public class Advice {
    long id = 0;
    double rating = -1d;
    String name = null;
    String adviceText = null;
    Category category = null;
    Image image = null;
    long ratingsCount;

    public Advice(long id, double rating, long ratingsCount, String name, String adviceText, Category category, Image image) {
        this.id = id;
        this.rating = rating;
        this.name = name;
        this.adviceText = adviceText;
        this.category = category;
        this.image = image;
        this.ratingsCount = ratingsCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdviceText() {
        return adviceText;
    }

    public void setAdviceText(String adviceText) {
        this.adviceText = adviceText;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public long getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(long ratingsCount) {
        this.ratingsCount = ratingsCount;
    }
}
