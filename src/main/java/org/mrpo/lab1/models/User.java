package org.mrpo.lab1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "`user`")
public class User {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "role")
    private String role;
    @Column(name = "name")
    private String name;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;

    public User() {}

    public User(long id, String role, String name, String username, String password) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String login) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + id +
                ", " + role +
                ", " + "\"" + name + "\"" +
                ", " + username +
                ", " + password + "}";
    }
}
