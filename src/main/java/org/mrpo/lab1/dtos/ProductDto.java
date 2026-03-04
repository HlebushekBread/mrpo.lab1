package org.mrpo.lab1.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String article;
    private String name;
    private Long categoryId;
    private String description;
    private Long manufacturerId;
    private Long providerId;
    private double price;
    private double discount;
    private int amount;
    private Long unitId;
    private String image;
}
