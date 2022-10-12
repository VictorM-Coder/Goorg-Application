package com.goorg.goorgjava.model.atividade;

public class Tarefa {
    private Long id;
    private String titulo;
    private boolean completo;

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
