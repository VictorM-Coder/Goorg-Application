package com.goorg.goorgjava.service;

import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.model.workspace.Workspace;
import com.goorg.goorgjava.repositories.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("WorkspaceService")
public class WorkspaceService implements ServiceInterface<Workspace> {
    @Autowired
    private WorkspaceRepository workspaceRepository;
    @Override
    public Workspace save(Workspace workspace) {
        return this.workspaceRepository.save(workspace);
    }

    @Override
    public Iterable<Workspace> saveAll(List<Workspace> workspaces) {
        return this.workspaceRepository.saveAll(workspaces);
    }

    @Override
    public Iterable<Workspace> getAll() {
        return this.workspaceRepository.findAll();
    }

    @Override
    public Optional<Workspace> getById(Long aLong) {
        return this.workspaceRepository.findById(aLong);
    }

    @Override
    public Workspace update(Long id, Workspace workspace) {
        Workspace oldWorkspace = this.findByIdOrThrowBadRequestException(id);
        this.updateData(oldWorkspace, workspace);
        return this.save(oldWorkspace);
    }

    @Override
    public void delete(Long id) {
        Workspace deletedWorkspace = this.findByIdOrThrowBadRequestException(id);
        this.workspaceRepository.delete(deletedWorkspace);
    }
    
    private void updateData(Workspace oldWorkspace, Workspace newWorkspace){
        oldWorkspace.setName(newWorkspace.getName());
        oldWorkspace.setDescription(newWorkspace.getDescription());
    }

    public Workspace findByIdOrThrowBadRequestException(Long id){
        return this.workspaceRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Anime not Found"));
    }

    
}
