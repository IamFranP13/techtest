package com.franpesal.techtest.configuration;

import java.io.Serial;

public class InvalidParameterException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 262067662626644005L;
    private final int errorCode;

    public InvalidParameterException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
