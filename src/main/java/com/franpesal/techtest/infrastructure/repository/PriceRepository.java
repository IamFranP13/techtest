package com.franpesal.techtest.infrastructure.repository;

import com.franpesal.techtest.domain.Price;
import com.franpesal.techtest.domain.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, PriceId> {
    @Query(value = "SELECT * FROM PRICES p WHERE p.PRODUCT_ID = :productId AND p.BRAND_ID = :brandId AND p.START_DATE <= :date AND p.END_DATE >= :date ORDER BY p.PRIORITY DESC LIMIT 1", nativeQuery = true)
    Optional<Price> findApplicablePrice(@Param("productId") Integer productId, @Param("brandId") Integer brandId, @Param("date") LocalDateTime date);

}
