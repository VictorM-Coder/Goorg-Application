package com.goorg.goorgjava.model;

import com.goorg.goorgjava.model.atividade.Tarefa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TarefaTest {
    @Test
    @DisplayName("A tarefa é iniciada corretamente")
    void tarefaCompleta_False_Quando_TarefaConstruida(){
        Tarefa tarefa = new Tarefa();
        Assertions.assertTrue(!tarefa.getStatus());
    }

    @Test
    @DisplayName("A tarefa é completa corretamente")
    void tarefaCompleta_False_Quando_TarefaCompleta(){
        Tarefa tarefa = new Tarefa();
        tarefa.completar();
        Assertions.assertTrue(tarefa.getStatus());
    }
}
