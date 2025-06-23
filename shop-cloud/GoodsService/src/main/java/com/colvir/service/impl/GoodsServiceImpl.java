package com.colvir.service.impl;

import com.colvir.dto.GoodsDto;
import com.colvir.mapper.GoodsMapper;
import com.colvir.repository.GoodsRepository;
import com.colvir.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository goodsRepository;
    private final GoodsMapper goodsMapper;

    @Override
    @Transactional(readOnly = true)
    public List<GoodsDto> getGoods() {
        return goodsRepository.findAll().stream().map(goodsMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GoodsDto> getGoodsById(Long id) {
        return goodsRepository.findById(id).map(goodsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GoodsDto> getGoodsByName(String name) {
        return goodsRepository.findByName(name).stream().map(goodsMapper::toDto).toList();
    }

    @Override
    @Transactional
    public void save(GoodsDto goalDto) {
        goodsRepository.save(goodsMapper.toEntity(goalDto));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (goodsRepository.findById(id).isPresent()) {
            goodsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
