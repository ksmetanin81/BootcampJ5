package com.colvir.mapper;

import com.colvir.domain.Goods;
import com.colvir.dto.GoodsDto;
import org.mapstruct.Mapper;

@Mapper
public interface GoodsMapper {

    GoodsDto toDto(Goods goods);

    Goods toEntity(GoodsDto goodsDto);
}
