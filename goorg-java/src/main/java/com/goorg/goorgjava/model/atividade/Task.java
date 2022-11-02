package com.goorg.goorgjava.model.atividade;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private boolean complete;

    public Task(){
        this.complete = false;
    }

    public Task(String title){
        this();
        this.title = title;
    }

    public Task(Long id, String title){
        this(title);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return complete == task.complete && id.equals(task.id) && title.equals(task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, complete);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", complete=" + complete +
                '}';
    }

    public void completar(){
        this.complete = true;
    }

    public void voltarEstado(){
        this.complete = false;
    }

    public boolean getStatus(){
        return this.complete;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String titulo) {
        this.title = titulo;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
