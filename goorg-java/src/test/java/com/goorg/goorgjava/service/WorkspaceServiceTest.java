package com.goorg.goorgjava.service;

import com.goorg.goorgjava.exception.BadRequestException;
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

@SpringBootTest
public class WorkspaceServiceTest implements ServiceTest{
    @Autowired
    private WorkspaceService workspaceService;

    private final WorkspaceCreator creator = new WorkspaceCreator();

    @MockBean
    private WorkspaceRepository workspaceRepository;

    @Override
    @Test
    @DisplayName("Um workspace é salvo corretamente")
    public void save_PersistItem_When_Sucess(){
        Mockito.when(this.workspaceRepository.save(creator.createValidItem())).thenReturn(this.creator.createValidItem());

        Workspace workspaceSaved = this.workspaceService.save(this.creator.createValidItem());
        Assertions.assertNotNull(workspaceSaved);
        Assertions.assertEquals(workspaceSaved, this.creator.createValidItem());
    }

    @Override
    @Test
    @DisplayName("Burcar o id de um workspace retorna um workspace quando é executado corretamente")
    public void findById_ReturnAItem_When_Success(){
        Mockito.when(this.workspaceRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.creator.createValidItem()));

        Long expectedID = 1L;
        Optional<Workspace> workspace = this.workspaceRepository.findById(expectedID);

        Assertions.assertFalse(workspace.isEmpty());
        Assertions.assertEquals(workspace.get(), creator.createValidItem());
        Assertions.assertEquals(workspace.get().getId(), expectedID);
    }

    @Override
    @Test
    @DisplayName("Retorna uma lista de workspaces válidos quando é executado com sucesso")
    public void findAll_ReturnItemList_When_Success() {
        Mockito.when(this.workspaceRepository.findAll()).thenReturn(this.creator.createValidItemsList());

        List<Workspace> workspaces = this.creator.createValidItemsList();
        List<Workspace> workspacesGetteds = (List<Workspace>) this.workspaceService.getAll();

        Assertions.assertNotNull(workspacesGetteds);
        Assertions.assertEquals(workspacesGetteds.size(), workspaces.size());

        for (int cont = 0; cont < workspaces.size(); cont++){
            Assertions.assertEquals(workspacesGetteds.get(cont), workspaces.get(cont));
        }
    }
    @Override
    @Test
    @DisplayName("Retorna um workspace atualizado e válido quando executado com sucesso")
    public void update_ReturnAUpdatedItem_When_Sucess() {
        Mockito.when(this.workspaceRepository.save(creator.createValidItem())).thenReturn(this.creator.createValidItem());
        Mockito.when(this.workspaceRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.creator.createValidItem()));

        Workspace workspace = this.creator.createValidItem();
        Workspace workspaceSaved = this.workspaceService.save(workspace);
        String newName = "novo titulo";
        workspaceSaved.setName(newName);

        Mockito.when(this.workspaceRepository.save(workspaceSaved)).thenReturn(workspaceSaved);
        Workspace workspaceUpdated = this.workspaceService.update(workspaceSaved.getId(), workspaceSaved);

        Assertions.assertEquals(workspaceUpdated.getId(), workspaceSaved.getId());
        Assertions.assertEquals(workspaceSaved, workspaceUpdated);
        Assertions.assertEquals(workspaceUpdated.getName(), newName);
    }

    @Override
    @Test
    @DisplayName("Lança uma exceção quando um id que não existente é passado como parâmetro")
    public void update_ThrowsBadRequestException_When_IdNotExists() {
        Workspace workspace = this.creator.createValidItem();
        Mockito.when(this.workspaceRepository.findById(workspace.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class , () -> this.workspaceService.update(workspace.getId(), workspace));
    }

    @Override
    @Test
    public void delete_executeDelete_When_Success() {
        Mockito.when(this.workspaceRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.creator.createValidItem()));

        this.workspaceService.delete(1L);
        Mockito.verify(workspaceRepository, Mockito.times(1)).delete(this.creator.createValidItem());
    }

    @Override
    @Test
    public void delete_ThrowsBadRequestException_When_IdNotExists() {
        Mockito.when(this.workspaceRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class , () -> this.workspaceService.delete(1L));
    }

    @Override
    @Test
    @DisplayName("Salva uma lista de Workspaces quando é executada corretamente")
    public void save_PersistItemsList_When_Success(){
        Mockito.when(this.workspaceRepository.saveAll(creator.createValidItemsList())).thenReturn(this.creator.createValidItemsList());

        List<Workspace> workspaceList = creator.createValidItemsList();
        List<Workspace> workspacesSaved = (List<Workspace>) this.workspaceService.saveAll(workspaceList);

        Assertions.assertNotNull(workspacesSaved);
        Assertions.assertEquals(workspacesSaved.size(), workspaceList.size());

        for (int cont = 0; cont < workspacesSaved.size(); cont++){
            Assertions.assertEquals(workspacesSaved.get(cont), workspaceList.get(cont));
        }
    }
}