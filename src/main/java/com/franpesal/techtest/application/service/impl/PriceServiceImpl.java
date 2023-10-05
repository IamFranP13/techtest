package com.franpesal.techtest.application.service.impl;

import com.franpesal.techtest.application.service.PriceUseCase;
import com.franpesal.techtest.domain.model.ApplicablePrice;
import com.franpesal.techtest.exceptions.PriceNotFoundException;
import com.franpesal.techtest.infrastructure.database.repository.impl.PriceRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceServiceImpl implements PriceUseCase {

    private final PriceRepositoryImpl priceRepository;
    private static final Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);

    public PriceServiceImpl(PriceRepositoryImpl priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public ApplicablePrice findApplicablePrice(Integer productId, Integer brandId, LocalDateTime date) {
        ApplicablePrice applicablePrice = priceRepository.findApplicablePrice(productId, brandId, date);
        if (applicablePrice == null) {
            logger.warn("No applicable price found for product {} and brand {} on date {}", productId, brandId, date);
            throw new PriceNotFoundException("No applicable price found for given parameters");
        }
        return applicablePrice;
    }
}

