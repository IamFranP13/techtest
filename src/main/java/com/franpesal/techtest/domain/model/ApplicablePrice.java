package com.franpesal.techtest.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicablePrice {
    private Integer id;
    private Integer brandId;
    private Integer productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private BigDecimal price;
    private Integer priority;
    private String currency;
}
