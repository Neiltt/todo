package com.uuu.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "date is required")
    private String date;
    @NotBlank(message = "note is required")
    private String note;
    @NotBlank(message = "task is required")
    private String task;

    public Event(String date, String note, String task) {
        this.date = date;
        this.note = note;
        this.task = task;
    }
}
