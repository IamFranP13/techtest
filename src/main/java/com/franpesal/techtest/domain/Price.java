package com.franpesal.techtest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "PRICES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price implements Serializable {

    @Serial
    private static final long serialVersionUID = 7074157876869338882L;

    @EmbeddedId
    private PriceId id;

    @Column(name = "PRICE_LIST", nullable = false)
    private Integer priceList;
    @Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @Column(name = "CURR", nullable = false)
    private String currency;
}
