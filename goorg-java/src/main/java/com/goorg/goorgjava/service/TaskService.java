package com.goorg.goorgjava.service;

import com.goorg.goorgjava.model.atividade.Task;
import com.goorg.goorgjava.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService extends CrudService<Task, TaskRepository> {
    public TaskService(TaskRepository repository) {
        super(repository);
    }

    @Transactional
    public List<Task> updateAll(List<Task> tasks){
        for (Task task: tasks){
            this.update(task);
        }
        return tasks;
    }

    @Override
    protected void updateData(Task oldTask, Task newTask){
        if (newTask.getTitle() != null) {
            oldTask.setTitle(newTask.getTitle());
        }
        oldTask.setComplete(newTask.getStatus());
    }
}
