package com.goorg.goorgjava.service;

import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.model.atividade.Task;
import com.goorg.goorgjava.repositories.TaskRepository;
import com.goorg.goorgjava.util.creator.creators.TaskCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    private TaskCreator creator = new TaskCreator();

    @MockBean
    private TaskRepository repository;

    @BeforeEach
    public void setUp(){
        Mockito.when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.creator.createValidItem()));
        Mockito.when(this.repository.save(this.creator.createValidItem())).thenReturn(this.creator.createValidItem());
        Mockito.when(this.repository.saveAll(creator.createValidItemsList())).thenReturn(this.creator.createValidItemsList());
        Mockito.when(this.repository.findAll()).thenReturn(this.creator.createValidItemsList());
    }

    @Override
    @Test
    @DisplayName("Uma tarefa é salva corretamente")
    public void save_PersistItem_When_Sucess(){
        Task taskCreated = this.taskService.save(this.creator.createValidItem());

        System.out.println(this.creator.createValidItem());
        Assertions.assertNotNull(taskCreated);
        Assertions.assertEquals(taskCreated, this.creator.createValidItem());
    }

    @Override
    @Test
    @DisplayName("Burcar o id de uma task retorna uma task quando é executado corretamente")
    public void findById_ReturnAItem_When_Success(){
        Long expectedID = 1L;
        Optional<Task> task = this.repository.findById(expectedID);

        Assertions.assertFalse(task.isEmpty());
        Assertions.assertTrue(task.get().equals(creator.createValidItem()));
        Assertions.assertEquals(task.get().getId(), expectedID);
    }

    @Override
    @Test
    @DisplayName("Retorna uma lista de tasks válidas quando é executado com sucesso")
    public void findAll_ReturnItemList_When_Success() {
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
        Task task = this.creator.createValidItem();
        Task taskSaved = this.taskService.save(task);
        String newTitle = "novo titulo";
        taskSaved.setTitle(newTitle);
        Mockito.when(this.repository.save(taskSaved)).thenReturn(taskSaved);
        Task TaskUpdated = this.taskService.update(taskSaved.getId(), taskSaved);

        Assertions.assertEquals(TaskUpdated.getId(), taskSaved.getId());
        Assertions.assertTrue(taskSaved.equals(TaskUpdated));
        Assertions.assertEquals(TaskUpdated.getTitle(), newTitle);
    }

    @Override
    @Test
    @DisplayName("Lança uma exceção quando um id que não existente é passado como parâmetro")
    public void update_ThrowsBadRequestException_When_IdNotExists() {
        Task task = this.creator.createValidItem();
        Mockito.when(this.repository.findById(task.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class , () -> this.taskService.update(task.getId(), task));
    }

    @Override
    @Test
    @DisplayName("Salva uma lista de tasks quando é executada corretamente")
    public void save_PersistItemsList_When_Success(){
        List<Task> taskList = creator.createValidItemsList();
        List<Task> tasksSaved = (List<Task>) this.taskService.saveAll(taskList);

        Assertions.assertNotNull(tasksSaved);
        Assertions.assertEquals(tasksSaved.size(), taskList.size());

        for (int cont = 0; cont < tasksSaved.size(); cont++){
            Assertions.assertEquals(tasksSaved.get(cont), taskList.get(cont));
        }
    }
}
