package com.goorg.goorgjava.util;

import com.goorg.goorgjava.model.atividade.Atividade;
import com.goorg.goorgjava.model.workspace.Workspace;

import java.util.ArrayList;
import java.util.List;

public class WorkspaceCreator {
    public static Workspace criarWorkspaceValido(){
        return new Workspace(1L, "workspace", "descrição",new ArrayList<Atividade>());
    }

    public static List<Workspace> criarListaDeWorkspacesValidos(){
        List<Workspace> workspaces = new ArrayList<>();
        workspaces.add(new Workspace(1L, "workspace", "descrição", new ArrayList<Atividade>()));
        workspaces.add(new Workspace(2L, "workspace2", "descrição", new ArrayList<Atividade>()));
        workspaces.add(new Workspace(3L, "workspace3", "descrição", new ArrayList<Atividade>()));

        return workspaces;
    }
}
