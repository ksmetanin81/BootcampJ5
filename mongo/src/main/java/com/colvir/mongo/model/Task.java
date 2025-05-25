package com.colvir.mongo.model;

import io.github.kaiso.relmongo.annotation.OneToOne;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "tasks")
public class Task {

    @Id
    private String id;
    private LocalDate date;
    private String name;
    private String description;

    @OneToOne
    private Goal goal;
}

