package org.ecom.DTOs;

import org.ecom.Model.Product;

public class ProductAdditionDTO {
    private String name;
    private String desc;
    private String category;
    private double price;
    private float rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Product createProductFromProductDTO() {
        Product product = new Product();
        product.setName(this.name);
        product.setDesc(this.desc);
        product.setCategory(this.category);
        product.setPrice(this.price);
        product.setRating(this.rating);
        return product;
    }
}
