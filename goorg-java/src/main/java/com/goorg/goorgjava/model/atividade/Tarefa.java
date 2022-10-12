package com.goorg.goorgjava.model.atividade;

public class Tarefa {
    private String titulo;
    private boolean completo;

    public Tarefa(){
        this.completo = false;
    }

    public void completar(){
        this.completo = true;
    }

    public boolean getStatus(){
        return this.completo;
    }
}
