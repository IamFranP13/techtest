package com.franpesal.techtest.infrastructure.controller;

import com.franpesal.techtest.application.dto.ApplicablePriceResponseDto;
import com.franpesal.techtest.application.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
@Tag(name = "Prices", description = "Techtest - Prices API")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @Operation(summary = "Finds the applicable price for a product id and brand id on a given date", description = "Finds the applicable price for a product id and brand id on a given date.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Applicable price found"),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(
                                    value = "400 BAD_REQUEST Required request parameter 'brandId' for method parameter type Integer is not present"))),
            @ApiResponse(responseCode = "404",
                    description = "Applicable price not found",
                    content = @Content(
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(
                                    value = "404 NOT_FOUND 'Applicable price not found for the given parameters'"))),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server error getting applicable price",
                    content = @Content(
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(
                                    value = "500 INTERNAL_SERVER_ERROR 'Something went wrong'")))
    })
    @GetMapping(value = "/applicable", produces = "application/json")
    public ResponseEntity<ApplicablePriceResponseDto> getApplicablePrice(
            @RequestParam @Parameter(description = "Product ID", example = "35455") Integer productId,
            @RequestParam @Parameter(description = "Brand ID", example = "1") Integer brandId,
            @RequestParam @Parameter(description = "Date with format yyyy-MM-dd HH:mm:ss", example = "2020-06-14 18:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date
    ) {
        try {
            ApplicablePriceResponseDto applicablePrice = priceService.findApplicablePrice(productId, brandId, date);
            if (applicablePrice == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Applicable price not found for the given parameters");
            }
            return ResponseEntity.ok(applicablePrice);
        } catch (InternalServerError e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }
}
