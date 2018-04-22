package com.borisenko.test.exceptions;

/**
 * Created by maxim on 21.04.18.
 */
public class TPSException extends RuntimeException{
    public TPSException() {
        super();
    }

    public TPSException(String message) {
        super(message);
    }

    public TPSException(String message, Throwable cause) {
        super(message, cause);
    }
}
