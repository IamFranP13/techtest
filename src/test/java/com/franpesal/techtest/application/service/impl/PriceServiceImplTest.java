package com.franpesal.techtest.application.service.impl;

import com.franpesal.techtest.configuration.PriceNotFoundException;
import com.franpesal.techtest.domain.model.ApplicablePrice;
import com.franpesal.techtest.infrastructure.database.repository.impl.PriceRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PriceServiceImplTest {

    private final PriceRepositoryImpl mockRepo = mock(PriceRepositoryImpl.class);
    private final PriceServiceImpl service = new PriceServiceImpl(mockRepo);

    @Test
    public void findApplicablePrice_ReturnsPrice() {
        ApplicablePrice mockPrice = new ApplicablePrice(); //
        when(mockRepo.findApplicablePrice(anyInt(), anyInt(), any())).thenReturn(mockPrice);

        ApplicablePrice result = service.findApplicablePrice(1, 1, LocalDateTime.now());

        assertNotNull(result);
        assertEquals(mockPrice, result);
    }

    @Test
    public void findApplicablePrice_ThrowsException() {

        when(mockRepo.findApplicablePrice(anyInt(), anyInt(), any())).thenReturn(null);

        assertThrows(PriceNotFoundException.class, () -> service.findApplicablePrice(1, 1, LocalDateTime.now()));
    }
}
