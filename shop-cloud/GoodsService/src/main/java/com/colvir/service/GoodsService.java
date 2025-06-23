package com.colvir.service;

import com.colvir.dto.GoodsDto;

import java.util.List;
import java.util.Optional;

public interface GoodsService {

    List<GoodsDto> getGoods();

    Optional<GoodsDto> getGoodsById(Long id);

    List<GoodsDto> getGoodsByName(String name);

    void save(GoodsDto goodsDto);

    boolean delete(Long id);
}
