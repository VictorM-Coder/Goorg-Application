package com.goorg.goorgjava.service;

import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.model.atividade.Task;
import com.goorg.goorgjava.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ServiceInterface<Task>{

    @Autowired
    private TaskRepository repository;
    @Override
    public Task save(Task task) {
        return this.repository.save(task);
    }

    @Override
    public Iterable<Task> saveAll(List<Task> tasks) {
        return this.repository.saveAll(tasks);
    }

    @Override
    public Iterable<Task> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Task> getById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Task update(Long id, Task task) {
        Task oldTask = this.findByIdOrThrowBadRequestException(id);
        this.updateData(oldTask, task);
        return this.save(oldTask);
    }

    private void updateData(Task oldTask, Task newTask){
        oldTask.setTitle(newTask.getTitle());
        oldTask.setComplete(newTask.getStatus());
    }

    public Task findByIdOrThrowBadRequestException(Long id){
        return this.repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Task not Found"));
    }

    @Override
    public Task delete(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
}
