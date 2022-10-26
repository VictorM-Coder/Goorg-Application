package com.goorg.goorgjava.util;

import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.model.workspace.Workspace;

import java.util.ArrayList;
import java.util.List;

public class WorkspaceCreator implements Creator<Workspace> {
    @Override
    public Workspace createValidItem(){
        return new Workspace(1L, "workspace", "descrição",new ArrayList<Activity>());
    }

    @Override
    public List<Workspace> createValidItemsList(){
        List<Workspace> workspaces = new ArrayList<>();
        workspaces.add(new Workspace(1L, "workspace", "descrição", new ArrayList<Activity>()));
        workspaces.add(new Workspace(2L, "workspace2", "descrição", new ArrayList<Activity>()));
        workspaces.add(new Workspace(3L, "workspace3", "descrição", new ArrayList<Activity>()));

        return workspaces;
    }
}
