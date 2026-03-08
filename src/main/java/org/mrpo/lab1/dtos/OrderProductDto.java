package org.mrpo.lab1.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductDto {
    private String productArticle;
    private int amount;
}
