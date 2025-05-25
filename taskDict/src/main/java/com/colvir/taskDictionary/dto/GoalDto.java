package com.colvir.taskDictionary.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GoalDto {

    @Min(0)
    private Long id;
    @NotEmpty
    private String name;
    private String motivation;
    private String resources;
    @FutureOrPresent
    private LocalDate deadline;
    private String description;
}
