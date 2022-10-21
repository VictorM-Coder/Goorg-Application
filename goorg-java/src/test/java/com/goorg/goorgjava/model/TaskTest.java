package com.goorg.goorgjava.model;

import com.goorg.goorgjava.model.atividade.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    @DisplayName("A tarefa é iniciada corretamente")
    void tarefaCompleta_False_Quando_TarefaConstruida(){
        Task task = new Task();
        Assertions.assertTrue(!task.getStatus());
    }

    @Test
    @DisplayName("A tarefa é completa corretamente")
    void tarefaCompleta_False_Quando_TarefaCompleta(){
        Task task = new Task();
        task.completar();
        Assertions.assertTrue(task.getStatus());
    }
}
