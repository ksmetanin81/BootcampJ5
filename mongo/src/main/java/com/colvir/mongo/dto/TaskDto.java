package com.colvir.mongo.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDto {

    @Min(0)
    private String id;
    @FutureOrPresent
    private LocalDate date;
    @NotEmpty
    private String name;
    private String description;
    @NotNull
    @Min(0)
    private String goalId;
}
