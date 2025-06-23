package com.colvir.feign;

import com.colvir.feign.dto.GoodsDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GoodsServiceFallback implements GoodsServiceClient {

    @Override
    public Optional<GoodsDto> getGoodsById(Long id) {
        return Optional.empty();
    }
}
