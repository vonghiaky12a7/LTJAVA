package com.example.KVBank.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "bankaccount")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = true)
    private int id;

    @Column(name = "Username", length = 50, nullable = false)
    private String username;

    @Column(name = "Balance", nullable = false)
    private int balance;

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
