package com.goorg.goorgjava.model.atividade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String titulo;

    @NotNull
    private boolean completo;

    @ManyToOne
    @JoinColumn
    private Atividade atividade;


    public Tarefa(){
        this.completo = false;
    }

    public Tarefa(String titulo){
        this();
        this.titulo = titulo;
    }

    public Tarefa(Long id, String titulo){
        this(titulo);
        this.id = id;
    }

    public void completar(){
        this.completo = true;
    }

    public void voltarEstado(){
        this.completo = false;
    }

    public boolean getStatus(){
        return this.completo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getId() {
        return id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
