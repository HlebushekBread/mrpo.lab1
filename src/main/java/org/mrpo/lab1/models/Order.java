package org.mrpo.lab1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderProduct> orderProducts;

    @Column(name = "order_date")
    @NotEmpty(message = "Order date cannot be empty")
    private Date orderDate;

    @Column(name = "delivery_date")
    @NotEmpty(message = "Delivery date cannot be empty")
    private Date deliveryDate;

    @ManyToOne
    @NotEmpty(message = "Delivery address cannot be empty")
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "receive_code")
    @NotEmpty(message = "Receive code cannot be empty")
    private int receiveCode;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
