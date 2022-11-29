package com.goorg.goorgjava.service;

import com.goorg.goorgjava.dto.workspace.WorkspaceDto;
import com.goorg.goorgjava.mapper.WorkspaceMapper;
import com.goorg.goorgjava.model.workspace.Workspace;
import com.goorg.goorgjava.repositories.WorkspaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceService extends CrudService<Workspace, WorkspaceDto, WorkspaceRepository, WorkspaceMapper> {

    public WorkspaceService(WorkspaceRepository repository, WorkspaceMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected void updateData(Workspace oldWorkspace, Workspace newWorkspace){
        oldWorkspace.setName(newWorkspace.getName());
        oldWorkspace.setDescription(newWorkspace.getDescription());
    }
}
