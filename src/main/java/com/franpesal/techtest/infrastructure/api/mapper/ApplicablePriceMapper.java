package com.franpesal.techtest.infrastructure.api.mapper;

import com.franpesal.techtest.domain.model.ApplicablePrice;
import com.franpesal.techtest.infrastructure.api.dto.ApplicablePriceResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApplicablePriceMapper {


    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "priority", target = "priority")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "currency", target = "currency")
    ApplicablePriceResponseDto toDto(ApplicablePrice applicablePrice);

    ApplicablePrice toDomainModel(ApplicablePriceResponseDto applicablePriceResponseDto);

}
