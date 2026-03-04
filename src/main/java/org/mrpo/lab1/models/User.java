package org.mrpo.lab1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "full_name")
    @NotEmpty(message = "Name cannot be empty")
    private String fullName;

    @Column(name = "username", unique = true)
    @Email
    @NotEmpty(message = "Email cannot be empty")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    public User() {}

    public User(long id, Role role, String fullName, String username, String password) {
        this.id = id;
        this.role = role;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }
}
