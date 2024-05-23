package com.example.KVBank.dao;

import java.io.Serial;

public class BankTransactionException extends Exception {
    @Serial
    private static final long serialVersionUID = -3128681006635769411L;
    public BankTransactionException(String message) {
        super(message);
    }
}
