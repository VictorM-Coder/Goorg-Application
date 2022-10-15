package com.goorg.goorgjava.util;

import com.goorg.goorgjava.model.atividade.Tarefa;

public class TarefaCreator {
    public static Tarefa criarTarefaValida(){
        return new Tarefa(1L,"Tarefa 1");
    }
}
