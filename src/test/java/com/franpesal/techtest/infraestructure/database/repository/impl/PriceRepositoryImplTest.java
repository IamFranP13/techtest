package com.franpesal.techtest.infraestructure.database.repository.impl;

import com.franpesal.techtest.domain.model.ApplicablePrice;
import com.franpesal.techtest.infrastructure.database.entity.PriceEntity;
import com.franpesal.techtest.infrastructure.database.mapper.PriceEntityMapper;
import com.franpesal.techtest.infrastructure.database.repository.JpaPriceRepository;
import com.franpesal.techtest.infrastructure.database.repository.impl.PriceRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class PriceRepositoryImplTest {

    @Autowired
    private JpaPriceRepository jpaPriceRepository;
    private final PriceEntityMapper priceEntityMapper = Mappers.getMapper(PriceEntityMapper.class);

    @Test
    public void testFindApplicablePrice() {

        PriceEntity testEntity = new PriceEntity();

        testEntity.setBrandId(1);
        testEntity.setProductId(35455);
        testEntity.setStartDate(LocalDateTime.of(2022, 6, 14, 0, 0));
        testEntity.setEndDate(LocalDateTime.of(2022, 12, 31, 23, 59));
        testEntity.setPriceList(1);
        testEntity.setPriority(0);
        testEntity.setPrice(new BigDecimal("35.50"));
        testEntity.setCurrency("EUR");

        jpaPriceRepository.save(testEntity);

        LocalDateTime testDate = LocalDateTime.of(2022, 6, 14, 10, 0);  // O una fecha específica de tu elección
        Integer testProductId = 35455;
        Integer testBrandId = 1;
        Optional<PriceEntity> result = jpaPriceRepository.findApplicablePrice(testProductId, testBrandId, testDate);

        assertThat(result).isNotEmpty();

    }

    @Test
    public void testFindApplicablePrice_MultipleRecords() {
        PriceRepositoryImpl repository = new PriceRepositoryImpl(jpaPriceRepository, priceEntityMapper);
        ApplicablePrice applicablePrice = repository.findApplicablePrice(35455, 1, LocalDateTime.parse("2020-06-14T16:00:00"));

        assertNotNull(applicablePrice);
        assertEquals(new BigDecimal("25.45"), applicablePrice.getPrice());
    }
}
