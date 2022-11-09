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
    public @ResponseBody Workspace postWorkspace(@Valid @RequestBody Workspace workspace){
        return this.workspaceService.save(workspace);
    }
    
    @PostMapping(path = "/all")
    public @ResponseBody Iterable<Workspace> postWorkspaces(@Valid @RequestBody List<Workspace> workspaces){
        return this.workspaceService.saveAll(workspaces);
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody Workspace updateWorkspace(@PathVariable Long id, @RequestBody Workspace workspace){
        return this.workspaceService.update(id, workspace);
    }
    
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Workspace> getAllWorkspaces(){
        return this.workspaceService.getAll();
    }
    
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Workspace> getWorkspaceById(@PathVariable Long id){
        return this.workspaceService.getById(id);
    }
    
    @DeleteMapping(path = "/{id}")
    public @ResponseBody void deleteWorkspace(@PathVariable Long id){
        this.workspaceService.delete(id);
    }
}
