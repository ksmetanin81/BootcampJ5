package com.colvir.service;

import com.colvir.dto.PayMethodDto;

import java.util.List;
import java.util.Optional;

public interface PayService {

    List<PayMethodDto> getPayMethods();

    Optional<PayMethodDto> getPayMethodById(Long id);

    void save(PayMethodDto goodsDto);

    boolean delete(Long id);

    void payGoods(Long goodsId, Long payMethodId);
}
