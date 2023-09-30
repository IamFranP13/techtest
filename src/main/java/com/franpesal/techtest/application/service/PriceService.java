package com.franpesal.techtest.application.service;

import com.franpesal.techtest.application.dto.ApplicablePriceResponseDto;

import java.time.LocalDateTime;

public interface PriceService {

    ApplicablePriceResponseDto findApplicablePrice(Integer productId, Integer brandId, LocalDateTime date);
}
