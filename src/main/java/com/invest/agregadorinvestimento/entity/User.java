package com.invest.agregadorinvestimento.entity;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity

@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name ="username")
    private String userName;
    @Column(name ="email")
    private String email;
    @Column(name ="password")
    private String password;

    @CreationTimestamp
    private Instant creationTimestamp;
    @UpdateTimestamp
    private Instant updatedTimestamp;

    

 

    public User(UUID userId, String userName, String email, String password, Instant creationTimestamp,
        Instant updatedTimestamp) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.creationTimestamp = creationTimestamp;
        this.updatedTimestamp = updatedTimestamp;
    }
    public User() {
    }
    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public Instant getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public void setUpdatedTimestamp(Instant updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
                + ", creationTimestamp=" + creationTimestamp + ", updatedTimestamp=" + updatedTimestamp + "]";
    }


    

}
