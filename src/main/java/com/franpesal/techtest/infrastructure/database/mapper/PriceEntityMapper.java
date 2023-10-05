package com.franpesal.techtest.infrastructure.database.mapper;

import com.franpesal.techtest.domain.model.ApplicablePrice;
import com.franpesal.techtest.infrastructure.database.entity.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    ApplicablePrice toDomainModel(PriceEntity priceEntity);

    PriceEntity toEntity(ApplicablePrice applicablePrice);
}
