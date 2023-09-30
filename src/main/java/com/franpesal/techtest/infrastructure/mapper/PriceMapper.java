package com.franpesal.techtest.infrastructure.mapper;

import com.franpesal.techtest.application.dto.ApplicablePriceResponseDto;
import com.franpesal.techtest.domain.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(target="productId", source="id.productId")
    @Mapping(target="brandId", source="id.brandId")
    @Mapping(target="startDate", source="id.startDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target="endDate", source="id.endDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source="price", target="price", numberFormat="#.00")
    ApplicablePriceResponseDto priceToApplicablePriceResponseDto(Price price);
}
