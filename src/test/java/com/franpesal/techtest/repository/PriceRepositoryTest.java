package com.franpesal.techtest.repository;


import com.franpesal.techtest.domain.Price;
import com.franpesal.techtest.infrastructure.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class PriceRepositoryTest {

    private final  PriceRepository priceRepository;
    public PriceRepositoryTest(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Test
    public void testFindHighestPriorityPrice() {
        LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 16, 0);
        Optional<Price> optionalPrice = priceRepository.findApplicablePrice(35455, 1, testDate);

        assertTrue(optionalPrice.isPresent());
        assertEquals(1, optionalPrice.get().getId().getPriority().intValue());

    }
}
