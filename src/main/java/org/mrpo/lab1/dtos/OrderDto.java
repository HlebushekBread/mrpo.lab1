package org.mrpo.lab1.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private long id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date orderDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date deliveryDate;
    private int receiveCode;
    private long statusId;
    private long addressId;
    private long userId;
    private List<OrderProductDto> orderProductDtos;
}
