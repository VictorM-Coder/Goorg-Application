package com.goorg.goorgjava.service;

import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.model.workspace.Workspace;
import com.goorg.goorgjava.repositories.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("WorkspaceService")
public class WorkspaceService implements ServiceInterface<Workspace> {
    @Autowired
    private WorkspaceRepository repository;
    @Override
    public Workspace save(Workspace workspace) {
        return this.repository.save(workspace);
    }

    @Override
    public Iterable<Workspace> saveAll(List<Workspace> workspaces) {
        return this.saveAll(workspaces);
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
