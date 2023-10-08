package com.franpesal.techtest.infrastructure.api.controller;

import com.franpesal.techtest.application.service.PriceUseCase;
import com.franpesal.techtest.configuration.PriceNotFoundException;
import com.franpesal.techtest.domain.model.ApplicablePrice;
import com.franpesal.techtest.infrastructure.api.PriceApi;
import com.franpesal.techtest.infrastructure.api.dto.ApplicablePriceResponseDto;
import com.franpesal.techtest.infrastructure.api.mapper.ApplicablePriceMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PriceController implements PriceApi {

    private final PriceUseCase priceService;
    private final ApplicablePriceMapper applicablePriceMapper;

    public PriceController(PriceUseCase priceService, ApplicablePriceMapper applicablePriceMapper) {
        this.priceService = priceService;
        this.applicablePriceMapper = applicablePriceMapper;
    }

    // En PriceController
    @Override
    public ResponseEntity<ApplicablePriceResponseDto> getApplicablePrice(
            @RequestParam Integer productId,
            @RequestParam Integer brandId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date ) {
        try {
            ApplicablePrice applicablePrice = priceService.findApplicablePrice(productId, brandId, date);
            if (applicablePrice == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Applicable price not found for the given parameters");
            }
            ApplicablePriceResponseDto applicablePriceResponseDto = applicablePriceMapper.toDto(applicablePrice);
            return ResponseEntity.ok(applicablePriceResponseDto);
        } catch (PriceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }




}

