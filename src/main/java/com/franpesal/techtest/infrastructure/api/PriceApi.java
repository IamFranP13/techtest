package com.franpesal.techtest.infrastructure.api;

import com.franpesal.techtest.infrastructure.api.dto.ApplicablePriceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Tag(name = "Prices", description = "Techtest - Prices API")
public interface PriceApi {

    @Operation(summary = "Finds the applicable price for a product id and brand id on a given date")
    @GetMapping(value = "/applicable", produces = "application/json")
    ResponseEntity<ApplicablePriceResponseDto> getApplicablePrice(
            @RequestParam @Parameter(description = "Product ID") Integer productId,
            @RequestParam @Parameter(description = "Brand ID") Integer brandId,
            @RequestParam @Parameter(description = "Date") LocalDateTime date);
}
