package com.example.KVBank.model;

public class BankAccountInfo {

    private int id;
    private String username;
    private int balance;

    public BankAccountInfo(int id, String username, int balance) {
        this.id = id;
        this.username = username;
        this.balance = balance;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
