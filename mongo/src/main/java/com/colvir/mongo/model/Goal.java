package com.colvir.mongo.model;

import io.github.kaiso.relmongo.annotation.OneToMany;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "goals")
public class Goal {

    @Id
    private String id;

    private String name;
    private String motivation;
    private String resources;
    private LocalDate deadline;
    private String description;

    @OneToMany
    private List<Task> tasks;
}