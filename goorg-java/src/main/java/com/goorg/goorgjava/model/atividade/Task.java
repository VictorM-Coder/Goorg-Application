package com.goorg.goorgjava.model.atividade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private boolean complete;

    @ManyToOne
    @JoinColumn
    private Activity activity;


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
}
