package com.invest.agregadorinvestimento.entity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;









@Entity

@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name ="username")
    private String username;
    @Column(name ="email")
    private String email;
    @Column(name ="password")
    private String password;

    @CreationTimestamp
    private Instant creationTimestamp;
    @UpdateTimestamp
    private Instant updatedTimestamp;
     
    // Relação One-to-Many com Account
    
    @OneToMany(mappedBy = "user")//Nome do atributo na classe Account
    private List<Account> accounts;

    
    public User() {
    }


    public User(UUID userId, String username, String email, String password, Instant creationTimestamp,
        Instant updatedTimestamp) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.creationTimestamp = creationTimestamp;
        this.updatedTimestamp = updatedTimestamp;
    }
  
    public UUID getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String userName) {
        this.username = userName;
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


    

  
    public List<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + username + ", email=" + email + ", password=" + password
                + ", creationTimestamp=" + creationTimestamp + ", updatedTimestamp=" + updatedTimestamp + "]";
    }


    

}
