package com.goorg.goorgjava.model.atividade.gerenciador;

import com.goorg.goorgjava.model.atividade.Tarefa;

public interface GerenciadorTarefas {
    void addTarefa(Tarefa tarefa);
    void removeTarefa(Tarefa tarefa);
    void updateTarefa(Tarefa tarefa);
}
