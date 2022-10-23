package com.goorg.goorgjava.model.atividade.gerenciador;

import com.goorg.goorgjava.model.atividade.Task;

public interface TaskManager {
    void addTask(Task task);
    void removeTask(Task task);
    void updateTask(Task task);
}
