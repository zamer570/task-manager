package com.example.taskmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    private String deadline;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) //cascade ensures operation of task also affects note eg delete, fetch lazy means note objects are loaded only when accessed
    @JoinColumn(name = "task-id") //This assumes your Note entity has a task_id field
    private List<Note> notes;
    private Boolean completed;
}
