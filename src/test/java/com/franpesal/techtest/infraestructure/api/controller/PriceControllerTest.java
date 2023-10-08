package com.franpesal.techtest.infraestructure.api.controller;

import com.franpesal.techtest.application.service.impl.PriceServiceImpl;
import com.franpesal.techtest.domain.model.ApplicablePrice;
import com.franpesal.techtest.infrastructure.api.controller.PriceController;
import com.franpesal.techtest.infrastructure.api.dto.ApplicablePriceResponseDto;
import com.franpesal.techtest.infrastructure.api.mapper.ApplicablePriceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PriceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PriceServiceImpl priceUseCase;

    @Mock
    private ApplicablePriceMapper applicablePriceMapper;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
    }

    @Test
    public void getApplicablePrice_ReturnsPrice() throws Exception {

        ApplicablePrice applicablePrice1 = new ApplicablePrice(1, 35455, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 1, new BigDecimal("35.50"), "EUR");

        ApplicablePriceResponseDto responseDto1 = new ApplicablePriceResponseDto(35455, 1, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 1, new BigDecimal("35.50"), "EUR");

        ApplicablePrice applicablePrice2 = new ApplicablePrice(1, 35455, LocalDateTime.parse("2020-06-14T15:00:00"), LocalDateTime.parse("2020-06-14T18:30:00"), 2, new BigDecimal("25.45"), "EUR");

        ApplicablePriceResponseDto responseDto2 = new ApplicablePriceResponseDto(35455, 1, LocalDateTime.parse("2020-06-14T15:00:00"), LocalDateTime.parse("2020-06-14T18:30:00"), 2, new BigDecimal("25.45"), "EUR");

        when(priceUseCase.findApplicablePrice(35455, 1, LocalDateTime.parse("2020-06-14T10:00:00"))).thenReturn(applicablePrice1);

        when(applicablePriceMapper.toDto(applicablePrice1)).thenReturn(responseDto1);

        mockMvc.perform(get("/prices/applicable")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2020-06-14 10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));

        when(priceUseCase.findApplicablePrice(35455, 1, LocalDateTime.parse("2020-06-14T16:00:00"))).thenReturn(applicablePrice2);

        when(applicablePriceMapper.toDto(applicablePrice2)).thenReturn(responseDto2);

        mockMvc.perform(get("/prices/applicable")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("date", "2020-06-14 16:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }


}
