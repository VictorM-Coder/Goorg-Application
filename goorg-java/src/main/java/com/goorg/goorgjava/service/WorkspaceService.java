package com.goorg.goorgjava.service;

import com.goorg.goorgjava.model.workspace.Workspace;
import com.goorg.goorgjava.repositories.WorkspaceRepository;
import org.springframework.stereotype.Service;

@Service("WorkspaceService")
public class WorkspaceService extends CrudService<Workspace, WorkspaceRepository> {
    public WorkspaceService(WorkspaceRepository repository) {
        super(repository);
    }

    @Override
    protected void updateData(Workspace oldWorkspace, Workspace newWorkspace){
        oldWorkspace.setName(newWorkspace.getName());
        oldWorkspace.setDescription(newWorkspace.getDescription());
    }
}
