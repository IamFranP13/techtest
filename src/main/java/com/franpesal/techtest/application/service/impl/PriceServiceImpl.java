package com.franpesal.techtest.application.service.impl;

import com.franpesal.techtest.application.dto.ApplicablePriceResponseDto;
import com.franpesal.techtest.application.service.PriceService;
import com.franpesal.techtest.exceptions.PriceNotFoundException;
import com.franpesal.techtest.infrastructure.mapper.PriceMapper;
import com.franpesal.techtest.infrastructure.repository.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;
    private static final Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);

    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public ApplicablePriceResponseDto findApplicablePrice(Integer productId, Integer brandId, LocalDateTime date) {
        return priceRepository.findApplicablePrice(productId, brandId, date)
                .map(price -> {
                    logger.info("Price found: {}", price);
                    return priceMapper.priceToApplicablePriceResponseDto(price);
                })
                .orElseThrow(() -> {
                    logger.warn("No applicable price found for product {} and brand {} on date {}", productId, brandId, date);
                    return new PriceNotFoundException("No applicable price found for given parameters");
                });
    }
}
