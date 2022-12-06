package com.goorg.goorgjava.service;

import com.goorg.goorgjava.dto.activity.TaskDto;
import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.mapper.TaskMapper;
import com.goorg.goorgjava.model.activity.Task;
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

import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceTest implements ServiceTest{
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    private final TaskCreator creator = new TaskCreator();

    @MockBean
    private TaskRepository repository;

    @Override
    @Test
    @DisplayName("Uma tarefa é salva corretamente")
    public void save_PersistItem_When_Sucess(){
        Task task = creator.createValidItem();
        TaskDto taskDto = taskMapper.toDto(task);

        when(repository.save(task)).thenReturn(task);
        TaskDto taskDtoSaved = taskService.save(taskDto);

        Assertions.assertNotNull(taskDtoSaved);
        Assertions.assertEquals(taskDtoSaved, taskDto);
    }

    @Override
    @Test
    @DisplayName("Burcar o id de uma task retorna uma task quando é executado corretamente")
    public void findById_ReturnAItem_When_Success(){
        Task task = creator.createValidItem();
        when(repository.findById(1L)).thenReturn(Optional.of(task));

        Optional<TaskDto> foundTaskDto = taskService.getById(1L);

        Assertions.assertTrue(foundTaskDto.isPresent());
        Assertions.assertEquals(taskMapper.toDto(task), foundTaskDto.get());
    }

    @Override
    @Test
    @DisplayName("Retorna uma lista de tasks válidas quando é executado com sucesso")
    public void findAll_ReturnItemList_When_Success() {
        List<Task> taskList = creator.createValidItemsList();

        when(repository.findAll()).thenReturn(taskList);
        List<TaskDto> foundTasksDto = (List<TaskDto>) taskService.getAll();

        Assertions.assertNotNull(foundTasksDto);
        Assertions.assertEquals(foundTasksDto.size(), taskList.size());

        for (int cont  = 0; cont  < taskList.size(); cont++){
            Assertions.assertEquals(foundTasksDto.get(cont), taskMapper.toDto(taskList.get(cont)));
        }
    }

    @Override
    @Test
    @DisplayName("Retorna uma task atualizada e válida quando executado com sucesso")
    public void update_executesSaveOnce_When_Sucess() {
        Task task = creator.createValidItem();

        when(repository.findById(1L)).thenReturn(Optional.of(task));
        when(repository.save(task)).thenReturn(task);

        taskService.update(taskMapper.toDto(task));

        Mockito.verify(repository, Mockito.times(1)).save(task);
    }

    @Override
    @Test
    @DisplayName("Lança uma exceção quando um id que não existente é passado como parâmetro")
    public void update_ThrowsBadRequestException_When_IdNotExists() {
        Task task = (creator.createValidItem());

        when(repository.findById(task.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class , () -> taskService.update(taskMapper.toDto(task)));
    }

    @Override
    @Test
    public void delete_executeDelete_When_Success() {
        Task task = this.creator.createValidItem();
        when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(task));

        this.taskService.delete(1L);
        Mockito.verify(repository, Mockito.times(1)).delete(task);
    }

    @Override
    @Test
    public void delete_ThrowsBadRequestException_When_IdNotExists() {
        when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class, ()-> this.taskService.delete(1L));
    }
    @Override
    @Test
    @DisplayName("Salva uma lista de tasks quando é executada corretamente")
    public void save_PersistItemsList_When_Success(){
        Task task = creator.createValidItem();

        when(this.repository.saveAll(List.of(task))).thenReturn(List.of(task));

        List<TaskDto> tasksSaved = (List<TaskDto>) this.taskService.saveAll(List.of(taskMapper.toDto(task)));

        Assertions.assertNotNull(tasksSaved);
        Assertions.assertEquals(tasksSaved.size(), 1);
        Assertions.assertEquals(tasksSaved.get(0), taskMapper.toDto(task));
    }
}
