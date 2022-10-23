package com.goorg.goorgjava.util;

import com.goorg.goorgjava.model.atividade.Task;

public class TaskCreator {
    public static Task createValidTask(){
        return new Task(1L,"Tarefa 1");
    }
}
