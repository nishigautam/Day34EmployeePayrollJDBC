package com.payrolljdbc;

public class ExceptionDB extends Exception {
    public enum ExceptionType {
        SQL_ERROR, UPDATE_FAIL, CONNECTION_FAILURE, INVALID_DATA
    }
    ExceptionType exceptionType;

    public ExceptionDB(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }
}
