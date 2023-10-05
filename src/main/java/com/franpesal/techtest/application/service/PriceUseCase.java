package com.franpesal.techtest.application.service;

import com.franpesal.techtest.domain.model.ApplicablePrice;

import java.time.LocalDateTime;

public interface PriceUseCase {

    ApplicablePrice findApplicablePrice(Integer productId, Integer brandId, LocalDateTime date);
}
