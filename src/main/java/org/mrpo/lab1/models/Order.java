package org.mrpo.lab1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.List;

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

    public long getId() {
        return id;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public Address getDeliveryAddress() {
        return address;
    }

    public User getUser() {
        return user;
    }

    public int getReceiveCode() {
        return receiveCode;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setDeliveryAddress(Address address) {
        this.address = address;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setReceiveCode(int receiveCode) {
        this.receiveCode = receiveCode;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
