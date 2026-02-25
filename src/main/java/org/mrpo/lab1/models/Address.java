package org.mrpo.lab1.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "delivery_address")
public class Address {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "address")
    private String address;

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" + this.id +
                ", " + "\"" + this.address + "\"" + "}";
    }
}
