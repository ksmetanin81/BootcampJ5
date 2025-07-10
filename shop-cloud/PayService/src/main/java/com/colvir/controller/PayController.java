package com.colvir.controller;

import com.colvir.dto.PayMethodDto;
import com.colvir.service.PayService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payMethods")
public class PayController {

    private final PayService payService;

    @GetMapping
    public List<PayMethodDto> getPayMethods() {
        return payService.getPayMethods();
    }

    @GetMapping("/{id}")
    public Optional<PayMethodDto> getPayMethodById(@PathVariable Long id) {
        return payService.getPayMethodById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid PayMethodDto payMethodDto) {
        if (payMethodDto.getId() != null) {
            throw new IllegalArgumentException("PayMethod id should be null");
        }
        payService.save(payMethodDto);
    }

    @PutMapping
    public void update(@RequestBody @Valid PayMethodDto payMethodDto) {
        payService.getPayMethodById(payMethodDto.getId()).ifPresentOrElse(it -> payService.save(payMethodDto), () -> {
            throw new NoSuchElementException("PayMethod not found");
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return payService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/pay/{goodsId}/{payMethodId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void payGoods(@PathVariable Long goodsId, @PathVariable Long payMethodId) {
        payService.payGoods(goodsId, payMethodId);
    }
}
