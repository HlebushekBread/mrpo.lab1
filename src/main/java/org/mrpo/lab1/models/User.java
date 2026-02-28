package org.mrpo.lab1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "role")
    @NotEmpty(message = "Role should be valid")
    private String role;

    @Column(name = "name")
    @NotEmpty(message = "Name cannot be empty")
    private String fullName;

    @Column(name = "username", unique = true)
    @Email
    @NotEmpty(message = "Email cannot be empty")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    public User() {}

    public User(long id, String role, String fullName, String username, String password) {
        this.id = id;
        this.role = role;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getFullName() {
        return fullName;
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

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
                ", " + "\"" + fullName + "\"" +
                ", " + username +
                ", " + password + "}";
    }
}
