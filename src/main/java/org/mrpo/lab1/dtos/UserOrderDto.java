package org.mrpo.lab1.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserOrderDto {
    private long addressId;
    private long userId;
    private List<OrderProductDto> orderProductDtos;
}