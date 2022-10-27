package com.goorg.goorgjava.service;

import com.goorg.goorgjava.model.workspace.Workspace;
import com.goorg.goorgjava.repositories.WorkspaceRepository;
import com.goorg.goorgjava.util.WorkspaceCreator;
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
public class WorkspaceServiceTest implements ServiceTest{
    @Autowired
    private WorkspaceService workspaceService;

    private WorkspaceCreator creator = new WorkspaceCreator();

    @MockBean
    private WorkspaceRepository repository;

    @BeforeEach
    public void setUp(){
        Mockito.when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.creator.createValidItem()));
        Mockito.when(this.repository.save(creator.createValidItem())).thenReturn(this.creator.createValidItem());
        Mockito.when(this.repository.saveAll(creator.createValidItemsList())).thenReturn(this.creator.createValidItemsList());
        Mockito.when(this.repository.findAll()).thenReturn(this.creator.createValidItemsList());
    }

    @Override
    @Test
    @DisplayName("Um workspace é salvo corretamente")
    public void save_PersistItem_When_Sucess(){
        Workspace workspaceSaved = this.workspaceService.save(this.creator.createValidItem());
        Assertions.assertNotNull(workspaceSaved);
        Assertions.assertEquals(workspaceSaved, this.creator.createValidItem());
    }

    @Override
    @Test
    @DisplayName("Burcar o id de um workspace retorna um workspace quando é executado corretamente")
    public void findById_ReturnAItem_When_Success(){
        Long expectedID = 1L;
        Optional<Workspace> workspace = this.repository.findById(expectedID);

        Assertions.assertFalse(workspace.isEmpty());
        Assertions.assertTrue(workspace.get().equals(creator.createValidItem()));
        Assertions.assertEquals(workspace.get().getId(), expectedID);
    }

    @Override
    @Test
    @DisplayName("Retorna uma lista de workspaces válidos quando é executado com sucesso")
    public void findAll_ReturnItemList_When_Success() {
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
    @DisplayName("Salva uma lista de Workspaces quando é executada corretamente")
    public void save_PersistItemsList_When_Success(){
        List<Workspace> workspaceList = creator.createValidItemsList();
        List<Workspace> workspacesSaved = (List<Workspace>) this.workspaceService.saveAll(workspaceList);

        Assertions.assertNotNull(workspacesSaved);
        Assertions.assertEquals(workspacesSaved.size(), workspaceList.size());

        for (int cont = 0; cont < workspacesSaved.size(); cont++){
            Assertions.assertEquals(workspacesSaved.get(cont), workspaceList.get(cont));
        }
    }
}
