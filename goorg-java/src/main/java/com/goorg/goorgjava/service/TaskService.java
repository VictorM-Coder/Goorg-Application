package com.goorg.goorgjava.service;

import com.goorg.goorgjava.dto.activity.TaskDto;
import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.mapper.TaskMapper;
import com.goorg.goorgjava.model.atividade.Activity;
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
        oldTask.setComplete(newTask.isComplete());
    }

    @Override
    public TaskDto save(TaskDto taskDto){
        Task enTask = this.mapper.toEntity(taskDto);
        Activity enActivity = enTask.getActivity();
        if(enActivity.getId() != null)
            return this.mapper.toDto(this.repository.save(enTask));
        else 
            throw new BadRequestException("Entity not Found");
    }
}
