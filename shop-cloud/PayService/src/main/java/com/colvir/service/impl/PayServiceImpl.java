package com.colvir.service.impl;

import com.colvir.domain.PayMethod;
import com.colvir.dto.PayMethodDto;
import com.colvir.feign.GoodsServiceClient;
import com.colvir.feign.dto.GoodsDto;
import com.colvir.mapper.PayMethodMapper;
import com.colvir.repository.PayMethodRepository;
import com.colvir.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final PayMethodRepository payMethodRepository;
    private final PayMethodMapper payMethodMapper;
    private final GoodsServiceClient goodsServiceClient;

    @Override
    @Transactional(readOnly = true)
    public List<PayMethodDto> getPayMethods() {
        return payMethodRepository.findAll().stream().map(payMethodMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PayMethodDto> getPayMethodById(Long id) {
        return payMethodRepository.findById(id).map(payMethodMapper::toDto);
    }

    @Override
    @Transactional
    public void save(PayMethodDto goalDto) {
        payMethodRepository.save(payMethodMapper.toEntity(goalDto));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (payMethodRepository.findById(id).isPresent()) {
            payMethodRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void payGoods(Long goodsId, Long payMethodId) {
        GoodsDto goodsDto = goodsServiceClient.getGoodsById(goodsId).orElseThrow(() -> new NoSuchElementException("Goods not found"));
        PayMethod payMethod = payMethodRepository.findById(payMethodId).orElseThrow(() -> new NoSuchElementException("PayMethod not found"));

        log.info("Receipt of payment {} price {} by card {}", goodsDto.getName(), goodsDto.getPrice(), payMethod.getCardNumber());
    }
}
