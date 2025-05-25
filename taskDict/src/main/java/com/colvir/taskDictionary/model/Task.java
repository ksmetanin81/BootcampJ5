package com.colvir.taskDictionary.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;
}

