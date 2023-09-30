package com.franpesal.techtest.exceptions;

import java.io.Serial;

public class PriceNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -7588510390123979507L;

    public PriceNotFoundException(String message) {
        super(message);
    }
}
