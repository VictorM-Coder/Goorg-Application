package com.goorg.goorgjava.service;

import com.goorg.goorgjava.exception.BadRequestException;
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

    }

    @Override
    @Test
    @DisplayName("Burcar o id de uma task retorna uma task quando é executado corretamente")
    public void findById_ReturnAItem_When_Success(){

    }

    @Override
    @Test
    @DisplayName("Retorna uma lista de tasks válidas quando é executado com sucesso")
    public void findAll_ReturnItemList_When_Success() {

    }

    @Override
    @Test
    @DisplayName("Retorna uma task atualizada e válida quando executado com sucesso")
    public void update_executesSaveOnce_When_Sucess() {

    }

    @Override
    @Test
    @DisplayName("Lança uma exceção quando um id que não existente é passado como parâmetro")
    public void update_ThrowsBadRequestException_When_IdNotExists() {

    }

    @Override
    @Test
    public void delete_executeDelete_When_Success() {

    }

    @Override
    @Test
    public void delete_ThrowsBadRequestException_When_IdNotExists() {

    }
    @Override
    @Test
    @DisplayName("Salva uma lista de tasks quando é executada corretamente")
    public void save_PersistItemsList_When_Success(){

    }

    @Test
    public void updateAll_updateATaskList_When_Success(){

    }
}
