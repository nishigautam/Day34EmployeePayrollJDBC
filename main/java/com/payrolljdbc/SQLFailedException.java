package com.payrolljdbc;

public class SQLFailedException extends Throwable {
    public SQLFailedException(String message) {
        super(message);
    }
}