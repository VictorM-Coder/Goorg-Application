package com.goorg.goorgjava.repository;

import com.goorg.goorgjava.model.workspace.Workspace;
import com.goorg.goorgjava.repositories.WorkspaceRepository;
import com.goorg.goorgjava.util.ActivityCreator;
import com.goorg.goorgjava.util.WorkspaceCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class WorkspaceRepositoryTest {
    @Autowired
    private WorkspaceRepository repository;

    @Test
    @DisplayName("Um workspace é salvo corretamente")
    public void save_Workspace_Quando_sucesso(){
        Workspace workspaceValido = WorkspaceCreator.createValidWorkspace();
        Workspace workspaceSalvo = this.repository.save(workspaceValido);
        Optional<Workspace> workspace = this.repository.findById(workspaceSalvo.getId());

        Assertions.assertTrue(workspace.isPresent());
        Assertions.assertEquals(workspaceSalvo, workspace.get());
    }

    @Test
    @DisplayName("Burcar o id de um workspace retorna um workspace quando é executado corretamente")
    void findById_ReturnaUmaListaDeWorkspace_Quando_Successo(){
        Workspace workspaceValido = WorkspaceCreator.createValidWorkspace();
        Workspace workspaceSalvo = this.repository.save(workspaceValido);

        Long id = workspaceValido.getId();

        Optional<Workspace> workspace = this.repository.findById(id);

        Assertions.assertTrue(workspace.isPresent());
        Assertions.assertFalse(workspace.isEmpty());
        Assertions.assertTrue(workspace.get().equals(workspaceSalvo));
    }

    @Test
    @DisplayName("Salva uma lista de workspaces quando é executada corretamente")
    void save_AWorkspacePersistido_Quando_Successo(){
        List<Workspace> workspaceValido = WorkspaceCreator.createValidWorkspaceList();
        List<Workspace> workspacesSalvos = (List<Workspace>) this.repository.saveAll(workspaceValido);

        Assertions.assertNotNull(workspacesSalvos);
        Assertions.assertFalse(workspacesSalvos.isEmpty());
        Assertions.assertEquals(workspacesSalvos.size(), ActivityCreator.createValidActivitiesList().size());

        for (int cont = 0; cont < workspacesSalvos.size(); cont++){
            Long id = workspacesSalvos.get(cont).getId();
            Optional<Workspace> workspaceSalvo = this.repository.findById(id);

            Assertions.assertTrue(workspaceSalvo.isPresent());
            Assertions.assertFalse(workspaceSalvo.isEmpty());
            Assertions.assertTrue(workspaceSalvo.get().equals(workspacesSalvos.get(cont)));
        }
    }
}
