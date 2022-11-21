package com.goorg.goorgjava.service;

import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.model.atividade.Task;
import com.goorg.goorgjava.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Task update(Task task) {
        Task oldTask = this.findByIdOrThrowBadRequestException(task.getId());
        this.updateData(oldTask, task);
        return this.save(oldTask);
    }

    @Override
    public void delete(Long id) {
        Task deletedTask = findByIdOrThrowBadRequestException(id);
        this.repository.delete(deletedTask);
    }

    @Transactional
    public List<Task> updateAll(List<Task> tasks){
        for (Task task: tasks){
            this.update(task);
        }
        return tasks;
    }

    public Task findByIdOrThrowBadRequestException(Long id){
        return this.repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Task not Found"));
    }

    private void updateData(Task oldTask, Task newTask){
        if (newTask.getTitle() != null) {
            oldTask.setTitle(newTask.getTitle());
        }
        oldTask.setComplete(newTask.getStatus());
    }
}
