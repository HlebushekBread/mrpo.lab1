package org.mrpo.lab1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "`product`")
public class Product {
    @Id
    @Column(name = "article")
    @NotEmpty(message = "Article cannot be empty")
    private String article;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(name = "price")
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
    @Min(value = 0, message = "Discount cannot be negative")
    private double discount;

    @Column(name = "amount")
    @Min(value = 0, message = "Amount cannot be negative")
    private double amount;

    @Column(name = "description")
    private String description;
}
