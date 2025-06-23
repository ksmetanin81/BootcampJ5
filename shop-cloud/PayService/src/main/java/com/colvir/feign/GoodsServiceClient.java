package com.colvir.feign;

import com.colvir.feign.dto.GoodsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "GoodsService", fallback = GoodsServiceFallback.class)
public interface GoodsServiceClient {

    @GetMapping("/api/goods/{id}")
    Optional<GoodsDto> getGoodsById(@PathVariable Long id);
}
