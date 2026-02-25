package org.mrpo.lab1.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "product_article")
    private String productArticle;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "delivery_date")
    private Date deliveryDate;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "receive_code")
    private int receiveCode;
    @Column(name = "status")
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

    public Address getAddress() {
        return address;
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

    public void setAddress(Address address) {
        this.address = address;
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
                ", " + this.address +
                ", " + this.userName +
                ", " + this.receiveCode +
                ", " + this.status + "}";
    }
}
