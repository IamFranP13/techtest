package com.franpesal.techtest.integration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
@SpringBootTest
public class PriceIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testPricesTableIsPopulated() {
        List<Map<String, Object>> prices = jdbcTemplate.queryForList("SELECT * FROM PRICES");

        // Verify that the table has at least one record
        assertFalse(prices.isEmpty());

        // Verify that the first record has the expected values
        Integer receivedBrandId = (Integer) prices.get(0).get("BRAND_ID");
        Timestamp receivedStartDate = (Timestamp) prices.get(0).get("START_DATE");
        Timestamp receivedEndDate = (Timestamp) prices.get(0).get("END_DATE");
        Integer receivedPriceList = (Integer) prices.get(0).get("PRICE_LIST");
        Integer receivedProductId= (Integer) prices.get(0).get("PRODUCT_ID");
        Integer receivedPriority= (Integer) prices.get(0).get("PRIORITY");
        BigDecimal receivedPrice = (BigDecimal) prices.get(0).get("PRICE");
        String receivedCurr = (String) prices.get(0).get("CURR");

        // Replace these with the values you expect in the first record in your PRICES table
        Integer expectedBrandId = 1;
        Timestamp expectedStartDate = Timestamp.valueOf(LocalDateTime.parse("2020-06-14 00:00:00", DATE_TIME_FORMATTER));
        Timestamp expectedEndDate = Timestamp.valueOf(LocalDateTime.parse("2020-12-31 23:59:59", DATE_TIME_FORMATTER));
        Integer expectedPriceList = 1;
        Integer expectedProductId= 35455;
        Integer expectedPriority= 0;
        BigDecimal expectedPrice = new BigDecimal("35.50");
        String expectedCurr = "EUR";

        assertEquals(receivedBrandId, expectedBrandId);
        assertEquals(receivedStartDate, expectedStartDate);
        assertEquals(receivedEndDate, expectedEndDate);
        assertEquals(receivedPriceList, expectedPriceList);
        assertEquals(receivedProductId, expectedProductId);
        assertEquals(receivedPriority, expectedPriority);
        assertEquals(receivedPrice, expectedPrice);
        assertEquals(receivedCurr, expectedCurr);
    }
}