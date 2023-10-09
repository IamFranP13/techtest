package com.franpesal.techtest.infrastructure.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data Transfer Object representing the applicable price for a product")
public class ApplicablePriceResponseDto {


    @Schema(description = "Product ID", example = "35455")
    private Integer productId;

    @Schema(description = "Brand ID", example = "1")
    private Integer brandId;

    @Schema(description = "Start Date of the price", example = "2020-06-14 00:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @Schema(description = "End Date of the price", example = "2020-12-31 23:59:59")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @Schema(description = "Price List ID", example = "1")
    private Integer priceList;

    @Schema(description = "Priority", example = "1")
    private Integer priority;

    @Schema(description = "Actual price", example = "35.50")
    @NumberFormat(pattern = "#0.00")
    private BigDecimal price;

    @Schema(description = "Currency", example = "EUR")
    private String currency;
}

