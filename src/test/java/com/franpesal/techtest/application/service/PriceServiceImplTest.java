package com.franpesal.techtest.application.service;

import com.franpesal.techtest.application.dto.ApplicablePriceResponseDto;
import com.franpesal.techtest.application.service.impl.PriceServiceImpl;
import com.franpesal.techtest.domain.Price;
import com.franpesal.techtest.domain.PriceId;
import com.franpesal.techtest.exceptions.PriceNotFoundException;
import com.franpesal.techtest.infrastructure.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class PriceServiceImplTest {

    @InjectMocks
    private PriceServiceImpl priceService;
    @Mock
    private PriceRepository priceRepository;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindApplicablePrice_PriceFound() {
        // Datos de prueba
        Integer productId = 1;
        Integer brandId = 1;
        LocalDateTime date = LocalDateTime.now();
        Price mockPrice = new Price();
        PriceId priceId = new PriceId();
        priceId.setProductId(1);
        priceId.setBrandId(1);
        priceId.setStartDate(LocalDateTime.of(2023, 9, 30, 0, 0));
        priceId.setEndDate(LocalDateTime.of(2023, 10, 1, 0, 0));
        priceId.setPriority(1);

        mockPrice.setId(priceId);
        mockPrice.setPriceList(1);
        mockPrice.setPrice(new BigDecimal("35.50"));
        mockPrice.setCurrency("EUR");

        ApplicablePriceResponseDto expectedDto = new ApplicablePriceResponseDto(
                1, 1,
                LocalDateTime.of(2023, 9, 30, 0, 0),
                LocalDateTime.of(2023, 10, 1, 0, 0),
                1,
                new BigDecimal("35.50"),
                "EUR"
        );

        when(priceRepository.findApplicablePrice(productId, brandId, date)).thenReturn(Optional.of(mockPrice));

        ApplicablePriceResponseDto result = priceService.findApplicablePrice(productId, brandId, date);

        assertEquals(expectedDto, result);
    }


    @Test
    public void testFindApplicablePrice_PriceNotFound() {

        Integer productId = 1;
        Integer brandId = 1;
        LocalDateTime date = LocalDateTime.now();

        when(priceRepository.findApplicablePrice(productId, brandId, date)).thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () -> priceService.findApplicablePrice(productId, brandId, date));

    }
}
