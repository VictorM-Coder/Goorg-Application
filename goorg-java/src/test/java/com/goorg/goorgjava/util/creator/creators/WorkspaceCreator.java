package com.goorg.goorgjava.util.creator.creators;

import com.goorg.goorgjava.model.workspace.Workspace;
import com.goorg.goorgjava.util.creator.Creator;

import java.util.ArrayList;
import java.util.List;

public class WorkspaceCreator implements Creator<Workspace> {
    @Override
    public Workspace createValidItem(){
        return new Workspace(1L, "workspace", "descrição");
    }

    
    @Override
    public List<Workspace> createValidItemsList(){
        List<Workspace> workspaces = new ArrayList<>();
        workspaces.add(new Workspace(1L, "workspace", "descrição"));
        workspaces.add(new Workspace(2L, "workspace2", "descrição"));
        workspaces.add(new Workspace(3L, "workspace3", "descrição"));

        return workspaces;
    }
}
