package com.goorg.goorgjava.mapper;

import com.goorg.goorgjava.dto.workspace.WorkspaceDto;
import com.goorg.goorgjava.model.workspace.Workspace;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceMapper implements ICrudMapper<Workspace, WorkspaceDto>{
    @Override
    public Workspace toEntity(WorkspaceDto workspaceDto) {
        Workspace workspace = new Workspace();
        workspace.setName(workspaceDto.getName());
        workspace.setDescription(workspaceDto.getDescription());
        workspace.setId(workspaceDto.getId());
        workspace.setActivities(workspaceDto.getActivities());

        return workspace;
    }

    @Override
    public WorkspaceDto toDto(Workspace workspace) {
        WorkspaceDto workspaceDto = new WorkspaceDto();
        workspaceDto.setName(workspace.getName());
        workspaceDto.setDescription(workspace.getDescription());
        workspaceDto.setId(workspace.getId());
        workspaceDto.setActivities(workspace.getActivities());

        return workspaceDto;
    }
}
