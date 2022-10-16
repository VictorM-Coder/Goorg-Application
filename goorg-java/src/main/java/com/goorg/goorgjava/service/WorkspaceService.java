package com.goorg.goorgjava.service;

import com.goorg.goorgjava.model.workspace.Workspace;

import java.util.List;
import java.util.Optional;

public class WorkspaceService implements Service<Workspace, Long> {
    @Override
    public void save(Workspace workspace) {

    }

    @Override
    public void saveAll(List<Workspace> workspaces) {

    }

    @Override
    public Iterable<Workspace> getAll() {
        return null;
    }

    @Override
    public Optional<Workspace> getById(Long aLong) {
        return Optional.empty();
    }
}
