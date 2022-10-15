package com.goorg.goorgjava.model;

import com.goorg.goorgjava.enums.Fase;
import com.goorg.goorgjava.model.atividade.Atividade;
import com.goorg.goorgjava.model.atividade.Tarefa;
import com.goorg.goorgjava.util.AtividadeCreator;
import com.goorg.goorgjava.util.TarefaCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AtividadeTeste {

    @Test
    @DisplayName("Uma atividade é construída com o status TODO")
    void statusAtividade_TODO_Quando_AtividadeCriada(){
        Atividade atividade = new Atividade();
        Assertions.assertTrue(atividade.getStatus().equals(Fase.TO_DO));
    }

    @Test
    @DisplayName("Uma atividade é iniciada corretamente")
    void statusAtividade_DOING_Quando_AtividadeIniciada(){
        Atividade atividade = new Atividade();
        atividade.iniciar();
        Assertions.assertTrue(atividade.getStatus().equals(Fase.DOING));
    }

    @Test
    @DisplayName("Uma atividade é completada corretamente")
    void statusAtividade_DONE_Quando_AtividadeCompletada(){
        Atividade atividade = new Atividade();
        atividade.completar();
        Assertions.assertTrue(atividade.getStatus().equals(Fase.DONE));
    }

    @Test
    @DisplayName("Uma atividade é reiniciada corretamente")
    void statusAtividade_TODO_Quando_AtividadeReiniciada(){
        Atividade atividade = new Atividade();
        atividade.iniciar();
        atividade.reiniciar();
        Assertions.assertTrue(atividade.getStatus().equals(Fase.TO_DO));
    }

    @Test
    @DisplayName("Uma tafera é adicionada corretamente")
    void tarefaAdicionada_Quando_AtividadeAdicionadaComSucesso(){
        Atividade atividade = AtividadeCreator.criarAtividadeValida();
        atividade.addTarefa(TarefaCreator.criarTarefaValida());
        Assertions.assertTrue(atividade.getTarefaPorId(atividade.getId()).isPresent());
    }

    @Test
    @DisplayName("Uma tafera é removida corretamente")
    void tarefaRemovida_Quando_AtividadeRemovidaComSucesso(){
        Atividade atividade = AtividadeCreator.criarAtividadeValida();
        Tarefa tarefa = TarefaCreator.criarTarefaValida();
        atividade.addTarefa(tarefa);
        atividade.removeTarefa(tarefa);
        Assertions.assertFalse(atividade.getTarefaPorId(atividade.getId()).isPresent());
    }

    @Test
    @DisplayName("Uma tafera é atualizada corretamente")
    void tarefaAtualizada_Quando_AtividadeAtualizadaComSucesso(){
        Atividade atividade = AtividadeCreator.criarAtividadeValida();
        Tarefa tarefa = TarefaCreator.criarTarefaValida();
        atividade.addTarefa(tarefa);
        tarefa.setTitulo("titulo atualizado");
        atividade.updateTarefa(tarefa);

        Assertions.assertFalse(atividade.getTarefas().isEmpty());
        Assertions.assertEquals(1, atividade.getTarefas().size());
        Assertions.assertTrue(atividade.getTarefaPorId(atividade.getId()).isPresent());

        Assertions.assertEquals(tarefa.getId() ,atividade.getTarefaPorId(atividade.getId()).get().getId());
    }

    @Test
    @DisplayName("Uma tafera é completa corretamente")
    void tarefaCompleta_True_Quando_AtividadeCompletadaComSucesso(){
        Atividade atividade = AtividadeCreator.criarAtividadeValida();
        Tarefa tarefa = TarefaCreator.criarTarefaValida();
        atividade.addTarefa(tarefa);
        atividade.completarTarefa(tarefa);

        Assertions.assertEquals(tarefa.getStatus() ,atividade.getTarefaPorId(atividade.getId()).get().getStatus());
    }

}
