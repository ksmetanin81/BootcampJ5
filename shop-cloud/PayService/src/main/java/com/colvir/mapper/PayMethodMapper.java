package com.colvir.mapper;

import com.colvir.domain.PayMethod;
import com.colvir.dto.PayMethodDto;
import org.mapstruct.Mapper;

@Mapper
public interface PayMethodMapper {

    PayMethodDto toDto(PayMethod payMethod);

    PayMethod toEntity(PayMethodDto payMethodDto);
}
