package com.franpesal.techtest.configuration;

import java.io.Serial;
import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -3363607545123045784L;
    private final Integer productId;
    private final Integer brandId;
    private final LocalDateTime date;

    public PriceNotFoundException(String message, Integer productId, Integer brandId, LocalDateTime date) {
        super(message);
        this.productId = productId;
        this.brandId = brandId;
        this.date = date;
    }


}
