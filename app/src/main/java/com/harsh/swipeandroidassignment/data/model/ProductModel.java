package com.harsh.swipeandroidassignment.data.model;

public class ProductModel {
    private String image;
    private double price;
    private String product_name;
    private String product_type;
    private double tax;

    public ProductModel(String image, double price, String product_name, String product_type, double tax) {
        this.image = image;
        this.price = price;
        this.product_name = product_name;
        this.product_type = product_type;
        this.tax = tax;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
}