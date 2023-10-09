package com.franpesal.techtest.infrastructure.database.mapper;

import com.franpesal.techtest.domain.model.ApplicablePrice;
import com.franpesal.techtest.infrastructure.database.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "priority", target = "priority")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "currency", target = "currency")
    ApplicablePrice toDomainModel(PriceEntity priceEntity);

    PriceEntity toEntity(ApplicablePrice applicablePrice);
}
