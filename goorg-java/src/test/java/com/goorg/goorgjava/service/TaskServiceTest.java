package com.goorg.goorgjava.service;

import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.model.atividade.Task;
import com.goorg.goorgjava.repositories.TaskRepository;
import com.goorg.goorgjava.util.creator.creators.TaskCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TaskServiceTest implements ServiceTest{
    @Autowired
    private TaskService taskService;

    private final TaskCreator creator = new TaskCreator();

    @MockBean
    private TaskRepository taskRepository;

    @Override
    @Test
    @DisplayName("Uma tarefa é salva corretamente")
    public void save_PersistItem_When_Sucess(){
        Mockito.when(this.taskRepository.save(this.creator.createValidItem())).thenReturn(this.creator.createValidItem());

        Task taskCreated = this.taskService.save(this.creator.createValidItem());

        System.out.println(this.creator.createValidItem());
        Assertions.assertNotNull(taskCreated);
        Assertions.assertEquals(taskCreated, this.creator.createValidItem());
    }

    @Override
    @Test
    @DisplayName("Burcar o id de uma task retorna uma task quando é executado corretamente")
    public void findById_ReturnAItem_When_Success(){
        Mockito.when(this.taskRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.creator.createValidItem()));
        Long expectedID = 1L;
        Optional<Task> task = this.taskRepository.findById(expectedID);

        Assertions.assertFalse(task.isEmpty());
        Assertions.assertEquals(task.get(), creator.createValidItem());
        Assertions.assertEquals(task.get().getId(), expectedID);
    }

    @Override
    @Test
    @DisplayName("Retorna uma lista de tasks válidas quando é executado com sucesso")
    public void findAll_ReturnItemList_When_Success() {
        Mockito.when(this.taskRepository.findAll()).thenReturn(this.creator.createValidItemsList());

        List<Task> tasks = this.creator.createValidItemsList();
        List<Task> tasksGetteds = (List<Task>) this.taskService.getAll();

        Assertions.assertNotNull(tasksGetteds);
        Assertions.assertEquals(tasksGetteds.size(), tasks.size());

        for (int cont = 0; cont < tasks.size(); cont++){
            Assertions.assertEquals(tasksGetteds.get(cont), tasks.get(cont));
        }
    }

    @Override
    @Test
    @DisplayName("Retorna uma task atualizada e válida quando executado com sucesso")
    public void update_ReturnAUpdatedItem_When_Sucess() {
        Mockito.when(this.taskRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.creator.createValidItem()));
        Mockito.when(this.taskRepository.save(this.creator.createValidItem())).thenReturn(this.creator.createValidItem());

        Task task = this.creator.createValidItem();
        Task taskSaved = this.taskService.save(task);
        String newTitle = "novo titulo";
        taskSaved.setTitle(newTitle);
        Mockito.when(this.taskRepository.save(taskSaved)).thenReturn(taskSaved);
        Task TaskUpdated = this.taskService.update(taskSaved);

        Assertions.assertEquals(TaskUpdated.getId(), taskSaved.getId());
        Assertions.assertEquals(taskSaved, TaskUpdated);
        Assertions.assertEquals(TaskUpdated.getTitle(), newTitle);
    }

    @Override
    @Test
    @DisplayName("Lança uma exceção quando um id que não existente é passado como parâmetro")
    public void update_ThrowsBadRequestException_When_IdNotExists() {
        Task task = this.creator.createValidItem();
        Mockito.when(this.taskRepository.findById(task.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class , () -> this.taskService.update(task));
    }

    @Override
    @Test
    public void delete_executeDelete_When_Success() {
        Mockito.when(this.taskRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.creator.createValidItem()));

        this.taskService.delete(1L);
        Mockito.verify(this.taskRepository, Mockito.times(1)).delete(this.creator.createValidItem());
    }

    @Override
    @Test
    public void delete_ThrowsBadRequestException_When_IdNotExists() {
        Mockito.when(this.taskRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class , () -> this.taskService.delete(1L));
    }
    @Override
    @Test
    @DisplayName("Salva uma lista de tasks quando é executada corretamente")
    public void save_PersistItemsList_When_Success(){
        Mockito.when(this.taskRepository.saveAll(creator.createValidItemsList())).thenReturn(this.creator.createValidItemsList());

        List<Task> taskList = creator.createValidItemsList();
        List<Task> tasksSaved = (List<Task>) this.taskService.saveAll(taskList);

        Assertions.assertNotNull(tasksSaved);
        Assertions.assertEquals(tasksSaved.size(), taskList.size());

        for (int cont = 0; cont < tasksSaved.size(); cont++){
            Assertions.assertEquals(tasksSaved.get(cont), taskList.get(cont));
        }
    }

    @Test
    public void updateAll_updateATaskList_When_Success(){
        Task task = this.creator.createValidItem();
        List<Task> tasks = List.of(task);
        Mockito.when(this.taskRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(task));
        Mockito.when(this.taskRepository.save(task)).thenReturn(task);

        String newTitle = "novo titulo";
        tasks.get(0).setTitle(newTitle);
        List<Task> tasksUptadeds = this.taskService.updateAll(tasks);

        Assertions.assertEquals(tasks.size(), tasksUptadeds.size());
        Assertions.assertEquals(tasksUptadeds.get(0).getId(), tasks.get(0).getId());
        Assertions.assertEquals(tasksUptadeds.get(0).getTitle(), newTitle);
    }
}
