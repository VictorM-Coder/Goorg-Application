package com.goorg.goorgjava.service;

import com.goorg.goorgjava.dto.activity.TaskDto;
import com.goorg.goorgjava.mapper.TaskMapper;
import com.goorg.goorgjava.model.atividade.Task;
import com.goorg.goorgjava.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService extends CrudService<Task, TaskDto, TaskRepository, TaskMapper> {

    public TaskService(TaskRepository repository, TaskMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected void updateData(Task oldTask, Task newTask){
        if (newTask.getTitle() != null) {
            oldTask.setTitle(newTask.getTitle());
        }
        oldTask.setComplete(newTask.getStatus());
    }
}
