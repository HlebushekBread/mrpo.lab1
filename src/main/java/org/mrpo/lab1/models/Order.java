package org.mrpo.lab1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "product_article")
    @NotEmpty(message = "Need at least one position in order")
    private String productArticle;

    @Column(name = "order_date")
    @NotEmpty(message = "Order date cannot be empty")
    private Date orderDate;

    @Column(name = "delivery_date")
    @NotEmpty(message = "Delivery date cannot be empty")
    private Date deliveryDate;

    @ManyToOne
    @NotEmpty(message = "Delivery address cannot be empty")
    @JoinColumn(name = "address_id")
    private Address deliveryAddress;

    @Column(name = "user_name")
    @NotEmpty(message = "User name cannot be empty")
    private String userName;

    @Column(name = "receive_code")
    @NotEmpty(message = "Receive code cannot be empty")
    private int receiveCode;

    @Column(name = "status")
    @NotEmpty(message = "Status cannot be empty")
    private String status;

    public long getId() {
        return id;
    }

    public String getProductArticle() {
        return productArticle;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getUserName() {
        return userName;
    }

    public int getReceiveCode() {
        return receiveCode;
    }

    public String getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProductArticle(String productArticle) {
        this.productArticle = productArticle;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setReceiveCode(int receiveCode) {
        this.receiveCode = receiveCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" + this.id +
                ", " + "\"" + this.productArticle + "\"" +
                ", " + this.orderDate +
                ", " + this.deliveryDate +
                ", " + this.deliveryAddress +
                ", " + this.userName +
                ", " + this.receiveCode +
                ", " + this.status + "}";
    }
}
