package com.colvir.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PayMethodDto {

    @Min(0)
    private Long id;
    @NotEmpty
    private String paySystem;
    @NotEmpty
    private String cardNumber;
}
