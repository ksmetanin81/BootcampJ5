package com.colvir.homework5_taskDictionary.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDto {

    @Min(0)
    private Long id;
    @FutureOrPresent
    private LocalDate date;
    @NotEmpty
    private String name;
    private String description;
}
