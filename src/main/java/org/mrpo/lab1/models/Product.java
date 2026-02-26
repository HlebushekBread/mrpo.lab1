package org.mrpo.lab1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

@Entity
@Table(name = "`product`")
public class Product {
    @Id
    @Column(name = "article")
    @NotEmpty(message = "Article cannot be empty")
    private String article;

    @Column(name = "name")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Column(name = "unit")
    @NotEmpty(message = "Unit cannot be empty")
    private String unit;

    @Column(name = "price")
    @NotEmpty(message = "Price cannot be empty")
    @Min(value = 0, message = "Price cannot be negative")
    private double price;

    @Column(name = "provider")
    private String provider;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "category")
    private String category;

    @Column(name = "discount")
    @NotEmpty(message = "Discount cannot be empty")
    @Min(value = 0, message = "Discount cannot be negative")
    private double discount;

    @Column(name = "amount")
    @NotEmpty(message = "Amount cannot be empty")
    @Min(value = 0, message = "Amount cannot be negative")
    private double amount;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    public Product() {}

    public String getArticle() {
        return article;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public double getPrice() {
        return price;
    }

    public String getProvider() {
        return this.provider;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public double getDiscount() {
        return discount;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" + this.article +
                ", " + this.name +
                ", " + this.unit +
                ", " + this.price +
                ", " + this.provider +
                ", " + this.manufacturer +
                ", " + this.category +
                ", " + this.discount +
                ", " + this.amount +
                ", " + "\"" + this.description + "\"" +
                ", " + (!Objects.equals(this.image, "") ? this.image : "No img") + "}";
    }
}
