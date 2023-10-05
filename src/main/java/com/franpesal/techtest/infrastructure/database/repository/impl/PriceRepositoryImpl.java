package com.franpesal.techtest.infrastructure.database.repository.impl;

import com.franpesal.techtest.domain.model.ApplicablePrice;
import com.franpesal.techtest.domain.port.PricePort;
import com.franpesal.techtest.infrastructure.database.entity.PriceEntity;
import com.franpesal.techtest.infrastructure.database.mapper.PriceEntityMapper;
import com.franpesal.techtest.infrastructure.database.repository.JpaPriceRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class PriceRepositoryImpl implements PricePort {


    private final JpaPriceRepository jpaPriceRepository;


    private final PriceEntityMapper priceEntityMapper;

    public PriceRepositoryImpl(JpaPriceRepository jpaPriceRepository, PriceEntityMapper priceEntityMapper) {
        this.jpaPriceRepository = jpaPriceRepository;
        this.priceEntityMapper = priceEntityMapper;
    }

    @Override
    public ApplicablePrice findApplicablePrice(Integer productId, Integer brandId, LocalDateTime date) {
        Optional<PriceEntity> optionalPriceEntity = jpaPriceRepository.findApplicablePrice(productId, brandId, date);
        return optionalPriceEntity.map(priceEntityMapper::toDomainModel).orElse(null);
    }
}
