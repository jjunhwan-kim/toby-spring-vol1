package com.example.tobyspring.user.sqlservice;

public class SqlNotFoundException extends RuntimeException {
    public SqlNotFoundException(String message) {
        super(message);
    }
}
