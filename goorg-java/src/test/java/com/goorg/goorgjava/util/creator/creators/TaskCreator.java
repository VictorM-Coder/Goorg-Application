package com.goorg.goorgjava.util.creator.creators;

import com.goorg.goorgjava.model.atividade.Task;
import com.goorg.goorgjava.util.creator.Creator;

import java.util.ArrayList;
import java.util.List;

public class TaskCreator implements Creator<Task> {
    @Override
    public Task createValidItem() {
        return new Task(1L,"Tarefa 1");
    }

    @Override
    public List<Task> createValidItemsList() {
        return new ArrayList<Task>(List.of(
                new Task(1L,"Tarefa 1"), new Task(2L,"Tarefa 2"), new Task(3L,"Tarefa 3")
        ));
    }
}
