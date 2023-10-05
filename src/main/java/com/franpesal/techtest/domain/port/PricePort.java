package com.franpesal.techtest.domain.port;

import com.franpesal.techtest.domain.model.ApplicablePrice;

import java.time.LocalDateTime;

public interface PricePort {
    ApplicablePrice findApplicablePrice(Integer productId, Integer brandId, LocalDateTime date);
}
