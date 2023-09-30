package com.franpesal.techtest.integration;

import com.franpesal.techtest.application.dto.ApplicablePriceResponseDto;
import com.franpesal.techtest.infrastructure.controller.PriceController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@DisplayName("Tests de peticiones al endpoint REST")
class PriceControllerIntegrationTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Integer PRODUCT_ID = 35455;
    private static final Integer BRAND_ID = 1;
    private static final ApplicablePriceResponseDto TEST1_EXPECTED_PRICE =
            new ApplicablePriceResponseDto(
                    35455,
                    1,
                    LocalDateTime.parse("2020-06-14 00:00:00", DATE_TIME_FORMATTER),
                    LocalDateTime.parse("2020-12-31 23:59:59", DATE_TIME_FORMATTER),
                    1,
                    new BigDecimal(35.50).setScale(2, RoundingMode.HALF_UP),
                    "EUR");
    private static final ApplicablePriceResponseDto TEST2_EXPECTED_PRICE =
            new ApplicablePriceResponseDto(
                    35455,
                    1,
                    LocalDateTime.parse("2020-06-14 15:00:00", DATE_TIME_FORMATTER),
                    LocalDateTime.parse("2020-06-14 18:30:00", DATE_TIME_FORMATTER),
                    2,
                    new BigDecimal(25.45).setScale(2, RoundingMode.HALF_UP),
                    "EUR");
    private static final ApplicablePriceResponseDto TEST3_EXPECTED_PRICE =
            new ApplicablePriceResponseDto(
                    35455,
                    1,
                    LocalDateTime.parse("2020-06-14 00:00:00", DATE_TIME_FORMATTER),
                    LocalDateTime.parse("2020-12-31 23:59:59", DATE_TIME_FORMATTER),
                    1,
                    new BigDecimal(35.50).setScale(2, RoundingMode.HALF_UP),
                    "EUR");
    private static final ApplicablePriceResponseDto TEST4_EXPECTED_PRICE =
            new ApplicablePriceResponseDto(
                    35455,
                    1,
                    LocalDateTime.parse("2020-06-15 00:00:00", DATE_TIME_FORMATTER),
                    LocalDateTime.parse("2020-06-15 11:00:00", DATE_TIME_FORMATTER),
                    3,
                    new BigDecimal(30.50).setScale(2, RoundingMode.HALF_UP),
                    "EUR");
    private static final ApplicablePriceResponseDto TEST5_EXPECTED_PRICE =
            new ApplicablePriceResponseDto(
                    35455,
                    1,
                    LocalDateTime.parse("2020-06-15 16:00:00", DATE_TIME_FORMATTER),
                    LocalDateTime.parse("2020-12-31 23:59:59", DATE_TIME_FORMATTER),
                    4,
                    new BigDecimal(38.95).setScale(2, RoundingMode.HALF_UP),
                    "EUR");


    @Autowired
    private PriceController princeController;

    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void test1Product355455brand1day14hour10() {
        LocalDateTime date = LocalDateTime.parse("2020-06-14 10:00:00", DATE_TIME_FORMATTER);
        ResponseEntity<ApplicablePriceResponseDto> price = princeController.getApplicablePrice(PRODUCT_ID, BRAND_ID, date);
        assertEquals(HttpStatus.OK, price.getStatusCode());
        assertEquals(TEST1_EXPECTED_PRICE, price.getBody());
    }
    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void test2Product355455brand1day14hour16() {
        LocalDateTime date = LocalDateTime.parse("2020-06-14 16:00:00", DATE_TIME_FORMATTER);
        ResponseEntity<ApplicablePriceResponseDto> price = princeController.getApplicablePrice(PRODUCT_ID, BRAND_ID, date);
        assertEquals(HttpStatus.OK, price.getStatusCode());
        assertEquals(TEST2_EXPECTED_PRICE, price.getBody());
    }
    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    void test3Product355455brand1day14hour21() {
        LocalDateTime date = LocalDateTime.parse("2020-06-14 21:00:00", DATE_TIME_FORMATTER);
        ResponseEntity<ApplicablePriceResponseDto> price = princeController.getApplicablePrice(PRODUCT_ID, BRAND_ID, date);
        assertEquals(HttpStatus.OK, price.getStatusCode());
        assertEquals(TEST3_EXPECTED_PRICE, price.getBody());
    }
    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
    void test4Product355455brand1day15hour10() {
        LocalDateTime date = LocalDateTime.parse("2020-06-15 10:00:00", DATE_TIME_FORMATTER);
        ResponseEntity<ApplicablePriceResponseDto> price = princeController.getApplicablePrice(PRODUCT_ID, BRAND_ID, date);
        assertEquals(HttpStatus.OK, price.getStatusCode());
        assertEquals(TEST4_EXPECTED_PRICE, price.getBody());
    }
    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
    void test5Product355455brand1day16hour21() {
        LocalDateTime date = LocalDateTime.parse("2020-06-16 21:00:00", DATE_TIME_FORMATTER);
        ResponseEntity<ApplicablePriceResponseDto> price = princeController.getApplicablePrice(PRODUCT_ID, BRAND_ID, date);
        assertEquals(HttpStatus.OK, price.getStatusCode());
        assertEquals(TEST5_EXPECTED_PRICE, price.getBody());
    }

    @Test
    @DisplayName("Test 7: petición BAD REQUEST")
    void test7BadRequest() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<ApplicablePriceResponseDto> response = testRestTemplate.getForEntity(
                "http://localhost:8080/prices/applicable?productId=5000",
                ApplicablePriceResponseDto.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
