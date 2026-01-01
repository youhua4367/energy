package com.example.energy_20231120306.exception;

/**
 * 业务异常
 */
public class BaseException extends RuntimeException {
    public BaseException() {

    }

    public BaseException(String message) {
        super(message);
    }
}
