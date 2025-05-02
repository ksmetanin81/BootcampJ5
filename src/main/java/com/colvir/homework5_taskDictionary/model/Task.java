package com.colvir.homework5_taskDictionary.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Task {

    private Long id;
    private LocalDate date;
    private String name;
    private String description;
}

