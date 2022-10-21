package com.goorg.goorgjava.model;

import com.goorg.goorgjava.enums.Phase;
import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.model.atividade.Task;
import com.goorg.goorgjava.util.ActivityCreator;
import com.goorg.goorgjava.util.TaskCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ActivityTest {

    @Test
    @DisplayName("Uma atividade é construída com o status TODO")
    void statusAtividade_TODO_Quando_AtividadeCriada(){
        Activity activity = new Activity();
        Assertions.assertTrue(activity.getStatus().equals(Phase.TO_DO));
    }

    @Test
    @DisplayName("Uma atividade é iniciada corretamente")
    void statusAtividade_DOING_Quando_AtividadeIniciada(){
        Activity activity = new Activity();
        activity.iniciar();
        Assertions.assertTrue(activity.getStatus().equals(Phase.DOING));
    }

    @Test
    @DisplayName("Uma atividade é completada corretamente")
    void statusAtividade_DONE_Quando_AtividadeCompletada(){
        Activity activity = new Activity();
        activity.completar();
        Assertions.assertTrue(activity.getStatus().equals(Phase.DONE));
    }

    @Test
    @DisplayName("Uma atividade é reiniciada corretamente")
    void statusAtividade_TODO_Quando_AtividadeReiniciada(){
        Activity activity = new Activity();
        activity.iniciar();
        activity.reiniciar();
        Assertions.assertTrue(activity.getStatus().equals(Phase.TO_DO));
    }

    @Test
    @DisplayName("Uma tafera é adicionada corretamente")
    void tarefaAdicionada_Quando_AtividadeAdicionadaComSucesso(){
        Activity activity = ActivityCreator.createValidActivity();
        activity.addTask(TaskCreator.createValidTask());
        Assertions.assertTrue(activity.getTarefaPorId(activity.getId()).isPresent());
    }

    @Test
    @DisplayName("Uma tafera é removida corretamente")
    void tarefaRemovida_Quando_AtividadeRemovidaComSucesso(){
        Activity activity = ActivityCreator.createValidActivity();
        Task task = TaskCreator.createValidTask();
        activity.addTask(task);
        activity.removeTask(task);
        Assertions.assertFalse(activity.getTarefaPorId(activity.getId()).isPresent());
    }

    @Test
    @DisplayName("Uma tafera é atualizada corretamente")
    void tarefaAtualizada_Quando_AtividadeAtualizadaComSucesso(){
        Activity activity = ActivityCreator.createValidActivity();
        Task task = TaskCreator.createValidTask();
        activity.addTask(task);
        task.setTitle("titulo atualizado");
        activity.updateTask(task);

        Assertions.assertFalse(activity.getTasks().isEmpty());
        Assertions.assertEquals(1, activity.getTasks().size());
        Assertions.assertTrue(activity.getTarefaPorId(activity.getId()).isPresent());

        Assertions.assertEquals(task.getId() , activity.getTarefaPorId(activity.getId()).get().getId());
    }

    @Test
    @DisplayName("Uma tafera é completa corretamente")
    void tarefaCompleta_True_Quando_AtividadeCompletadaComSucesso(){
        Activity activity = ActivityCreator.createValidActivity();
        Task task = TaskCreator.createValidTask();
        activity.addTask(task);
        activity.completarTarefa(task);

        Assertions.assertEquals(task.getStatus() , activity.getTarefaPorId(activity.getId()).get().getStatus());
    }

}
