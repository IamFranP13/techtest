package com.franpesal.techtest.infrastructure.api.mapper;

import com.franpesal.techtest.domain.model.ApplicablePrice;
import com.franpesal.techtest.infrastructure.api.dto.ApplicablePriceResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicablePriceMapper {

    ApplicablePriceResponseDto toDto(ApplicablePrice applicablePrice);

    ApplicablePrice toDomainModel(ApplicablePriceResponseDto applicablePriceResponseDto);

}
