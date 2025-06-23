package com.colvir.controller;

import com.colvir.dto.GoodsDto;
import com.colvir.service.GoodsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping
    public List<GoodsDto> getGoods() {
        return goodsService.getGoods();
    }

    @GetMapping("/{id}")
    public Optional<GoodsDto> getGoodsById(@PathVariable Long id) {
        return goodsService.getGoodsById(id);
    }

    @GetMapping("/byName/{name}")
    public List<GoodsDto> getGoodsByName(@PathVariable String name) {
        return goodsService.getGoodsByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid GoodsDto goodsDto) {
        if (goodsDto.getId() != null) {
            throw new IllegalArgumentException("Goal id should be null");
        }
        goodsService.save(goodsDto);
    }

    @PutMapping
    public void update(@RequestBody @Valid GoodsDto goodsDto) {
        goodsService.getGoodsById(goodsDto.getId()).ifPresentOrElse(it -> goodsService.save(goodsDto), () -> {
            throw new NoSuchElementException("Goal not found");
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return goodsService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
