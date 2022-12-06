package com.goorg.goorgjava.service;

import com.goorg.goorgjava.dto.activity.ActivityDto;
import com.goorg.goorgjava.dto.workspace.WorkspaceDto;
import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.mapper.WorkspaceMapper;
import com.goorg.goorgjava.model.activity.Activity;
import com.goorg.goorgjava.model.workspace.Workspace;
import com.goorg.goorgjava.repositories.WorkspaceRepository;
import com.goorg.goorgjava.util.creator.creators.WorkspaceCreator;
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
public class WorkspaceServiceTest implements ServiceTest{
    @Autowired
    private WorkspaceService workspaceService;

    @Autowired
    private WorkspaceMapper workspaceMapper;

    private final WorkspaceCreator creator = new WorkspaceCreator();

    @MockBean
    private WorkspaceRepository repository;

    @Override
    @Test
    @DisplayName("Um workspace é salvo corretamente")
    public void save_PersistItem_When_Sucess(){
        Workspace workspace = creator.createValidItem();
        WorkspaceDto workspaceDto = workspaceMapper.toDto(workspace);

        when(repository.save(workspace)).thenReturn(workspace);
        WorkspaceDto workspaceDtoSaved = workspaceService.save(workspaceDto);

        Assertions.assertNotNull(workspaceDtoSaved);
        Assertions.assertEquals(workspaceDtoSaved, workspaceDto);
    }

    @Override
    @Test
    @DisplayName("Burcar o id de um workspace retorna um workspace quando é executado corretamente")
    public void findById_ReturnAItem_When_Success(){
        Workspace workspace = creator.createValidItem();
        when(repository.findById(1L)).thenReturn(Optional.of(workspace));

        Optional<WorkspaceDto> foundworkspaceDto = workspaceService.getById(1L);

        Assertions.assertTrue(foundworkspaceDto.isPresent());
        Assertions.assertEquals(workspaceMapper.toDto(workspace), foundworkspaceDto.get());
    }

    @Override
    @Test
    @DisplayName("Retorna uma lista de workspaces válidos quando é executado com sucesso")
    public void findAll_ReturnItemList_When_Success() {
        List<Workspace> workspaceList = creator.createValidItemsList();

        when(repository.findAll()).thenReturn(workspaceList);
        List<WorkspaceDto> foundworkspaceDto = (List<WorkspaceDto>) workspaceService.getAll();

        Assertions.assertNotNull(foundworkspaceDto);
        Assertions.assertEquals(foundworkspaceDto.size(), workspaceList.size());

        for (int cont  = 0; cont  < workspaceList.size(); cont++){
            Assertions.assertEquals(foundworkspaceDto.get(cont), workspaceMapper.toDto(workspaceList.get(cont)));
        }
    }
    @Override
    @Test
    @DisplayName("Retorna um workspace atualizado e válido quando executado com sucesso")
    public void update_executesSaveOnce_When_Sucess() {
        Workspace workspace = creator.createValidItem();

        when(repository.findById(1L)).thenReturn(Optional.of(workspace));
        when(repository.save(workspace)).thenReturn(workspace);

        workspaceService.update(workspaceMapper.toDto(workspace));

        Mockito.verify(repository, Mockito.times(1)).save(workspace);
    }

    @Override
    @Test
    @DisplayName("Lança uma exceção quando um id que não existente é passado como parâmetro")
    public void update_ThrowsBadRequestException_When_IdNotExists() {
        Workspace workspace = (creator.createValidItem());

        when(repository.findById(workspace.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class , () -> workspaceService.update(workspaceMapper.toDto(workspace)));
    }

    @Override
    @Test
    public void delete_executeDelete_When_Success() {
        Workspace workspace = this.creator.createValidItem();
        when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(workspace));

        this.workspaceService.delete(1L);
        Mockito.verify(repository, Mockito.times(1)).delete(workspace);
    }

    @Override
    @Test
    public void delete_ThrowsBadRequestException_When_IdNotExists() {
        when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class, ()-> this.workspaceService.delete(1L));
    }

    @Override
    @Test
    @DisplayName("Salva uma lista de Workspaces quando é executada corretamente")
    public void save_PersistItemsList_When_Success(){
        Workspace workspace = creator.createValidItem();

        when(this.repository.saveAll(List.of(workspace))).thenReturn(List.of(workspace));

        List<WorkspaceDto> workspaceDtosSaved = (List<WorkspaceDto>) this.workspaceService.saveAll(List.of(workspaceMapper.toDto(workspace)));

        Assertions.assertNotNull(workspaceDtosSaved);
        Assertions.assertEquals(workspaceDtosSaved.size(), 1);
        Assertions.assertEquals(workspaceDtosSaved.get(0), workspaceMapper.toDto(workspace));
    }
}