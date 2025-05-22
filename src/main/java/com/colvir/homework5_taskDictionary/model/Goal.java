package com.colvir.homework5_taskDictionary.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String motivation;
    private String resources;
    private LocalDate deadline;
    private String description;

    @OneToMany(mappedBy = "goal")
    private List<Task> tasks;
}