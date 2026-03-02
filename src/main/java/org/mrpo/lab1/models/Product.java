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

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(name = "price")
    @NotEmpty(message = "Price cannot be empty")
    @Min(value = 0, message = "Price cannot be negative")
    private double price;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

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

    public Unit getUnit() {
        return unit;
    }

    public double getPrice() {
        return price;
    }

    public Provider getProvider() {
        return provider;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Category getCategory() {
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

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setCategory(Category category) {
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
}
