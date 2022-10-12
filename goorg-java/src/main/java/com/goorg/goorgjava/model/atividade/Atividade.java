package com.goorg.goorgjava.model.atividade;

import com.goorg.goorgjava.enums.Fase;

import java.time.LocalDate;
import java.util.List;

public class Atividade {
    private String titulo;
    private String descricao;
    private List<String> anotacoes;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private List<Tarefa> tarefas;
    private TagDePrioridade tagDePrioridade;
    private String nomeWorkspace;
    private Fase fase;

    public void iniciar(){

    }

    public void completar(){

    }

    public void reiniciar(){

    }

    public void completarTarefa(Tarefa tarefa){

    }
}
