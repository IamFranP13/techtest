package com.franpesal.techtest.e2e;

import com.franpesal.techtest.infrastructure.api.dto.ApplicablePriceResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceEndToEndTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetApplicablePrice() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String expectedDateStr = "2020-06-14 00:00:00";
        LocalDateTime expectedStartDate = LocalDateTime.parse(expectedDateStr, formatter);
        LocalDateTime expectedEndDate = LocalDateTime.parse("2020-12-31 23:59:59", formatter);

        String url = "/prices/applicable?productId=35455&brandId=1&date=" + expectedDateStr;
        ResponseEntity<ApplicablePriceResponseDto> response = restTemplate.getForEntity(url, ApplicablePriceResponseDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ApplicablePriceResponseDto applicablePriceResponseDto = response.getBody();

        assertEquals(35455, applicablePriceResponseDto.getProductId());
        assertEquals(1, applicablePriceResponseDto.getBrandId());
        assertEquals(expectedStartDate, applicablePriceResponseDto.getStartDate());
        assertEquals(expectedEndDate, applicablePriceResponseDto.getEndDate());
        assertEquals(1, applicablePriceResponseDto.getPriceList());
        assertEquals(0, applicablePriceResponseDto.getPriority());
        assertEquals(35.50, applicablePriceResponseDto.getPrice().doubleValue(), 0.001);
        assertEquals("EUR", applicablePriceResponseDto.getCurrency());
    }

    @Test
    public void testMissingParameter() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String expectedDateStr = "2020-06-14 10:00:00";
        String url = "/prices/applicable?test=35455&brandId=1&date=" + expectedDateStr;

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        Map<String, Object> responseBody = response.getBody();

        assertEquals(400, responseBody.get("status"));
        assertEquals("Missing request parameter", responseBody.get("message"));
        assertEquals("Required request parameter 'productId' for method parameter type Integer is not present", responseBody.get("details"));
    }

    @Test
    public void testNoApplicablePriceFound() {
        String url = "/prices/applicable?productId=56000&brandId=1&date=2020-06-14 10:00:00";

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, Object> body = response.getBody();

        assertEquals(400, body.get("status"));
        assertEquals("400 BAD_REQUEST \"No applicable price found for given parameters\"", body.get("message"));
        assertEquals("Response status exception", body.get("details"));
    }
}
