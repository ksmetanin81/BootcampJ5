package com.colvir.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsDto {

    @Min(0)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private BigDecimal price;
    private String description;
}
