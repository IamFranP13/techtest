package com.franpesal.techtest.integration;

import com.franpesal.techtest.application.service.impl.PriceServiceImpl;
import com.franpesal.techtest.domain.model.ApplicablePrice;
import com.franpesal.techtest.infrastructure.database.repository.impl.PriceRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class PriceServiceImplIntegrationTest  {

    @InjectMocks
    private PriceServiceImpl priceService;

    @Mock
    private PriceRepositoryImpl priceRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindApplicablePrice_at10() {
        testHelper(LocalDateTime.of(2020, 6, 14, 10, 0),
                new ApplicablePrice(1, 1, 35455,
                        LocalDateTime.of(2020, 6, 14, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                        1,
                        new BigDecimal("35.50"),0, "EUR"));
    }

    @Test
    void testFindApplicablePrice_at16() {
        testHelper(LocalDateTime.of(2020, 6, 14, 16, 0),
                new ApplicablePrice(2,1, 35455,
                        LocalDateTime.of(2020, 6, 14, 15, 0),
                        LocalDateTime.of(2020, 6, 14, 18, 30),
                        2,
                        new BigDecimal("25.45"),1, "EUR"));
    }


    @Test
    void testFindApplicablePrice_at21() {
        testHelper(LocalDateTime.of(2020, 6, 14, 16, 0),
                new ApplicablePrice(3, 1, 35455,
                        LocalDateTime.of(2020, 6, 14, 15, 0),
                        LocalDateTime.of(2020, 6, 14, 18, 30),
                        2,
                        new BigDecimal("25.45"), 1, "EUR"));
    }

    @Test
    void testFindApplicablePrice_at10_nextDay() {
        testHelper(LocalDateTime.of(2020, 6, 15, 10, 0), new ApplicablePrice(1, 1, 35455,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1,
                new BigDecimal("35.50"),0, "EUR"));
    }

    @Test
    void testFindApplicablePrice_at21_twoDaysLater() {
        testHelper(LocalDateTime.of(2020, 6, 16, 21, 0),new ApplicablePrice(1, 1, 35455,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1,
                new BigDecimal("35.50"),0, "EUR"));
    }

    void testHelper(LocalDateTime dateTime, ApplicablePrice expectedPrice) {
        Integer productId = 35455;
        Integer brandId = 1;

        when(priceRepository.findApplicablePrice(productId, brandId, dateTime)).thenReturn(expectedPrice);

        ApplicablePrice applicablePrice = priceService.findApplicablePrice(productId, brandId, dateTime);

        assertNotNull(applicablePrice);
        assertEquals(expectedPrice.getPrice(), applicablePrice.getPrice());

    }
}

