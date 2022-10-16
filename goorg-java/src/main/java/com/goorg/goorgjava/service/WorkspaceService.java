package com.goorg.goorgjava.service;

import com.goorg.goorgjava.model.workspace.Workspace;
import com.goorg.goorgjava.repositories.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class WorkspaceService implements Service<Workspace, Long> {
    @Autowired
    private WorkspaceRepository repository;
    @Override
    public void save(Workspace workspace) {
        this.repository.save(workspace);
    }

    @Override
    public void saveAll(List<Workspace> workspaces) {
        this.saveAll(workspaces);
    }

    @Override
    public Iterable<Workspace> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Workspace> getById(Long aLong) {
        return this.repository.findById(aLong);
    }
}
