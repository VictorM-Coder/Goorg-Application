package com.goorg.goorgjava.controller.workspace;

import com.goorg.goorgjava.model.workspace.Workspace;
import com.goorg.goorgjava.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/workspace")
public class WorkspaceController {
    @Autowired
    private WorkspaceService workspaceService;

    @PostMapping
    public @ResponseBody void postWorkspace(@Valid @RequestBody Workspace workspace){
        this.workspaceService.save(workspace);
    }

    @PostMapping(path = "/all")
    public @ResponseBody void postWorkspaces(@Valid @RequestBody List<Workspace> workspaces){
        this.workspaceService.saveAll(workspaces);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Workspace> getAllWorkspaces(){
        return this.workspaceService.getAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Workspace> getWorkspacePorId(@PathVariable Long id){
        return this.workspaceService.getById(id);
    }
}
