package com.goorg.goorgjava.controller.workspace;

import com.goorg.goorgjava.controller.CrudController;
import com.goorg.goorgjava.dto.workspace.WorkspaceDto;
import com.goorg.goorgjava.service.WorkspaceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path = "/workspace")
public class WorkspaceController extends CrudController<WorkspaceDto, WorkspaceService> {

    public WorkspaceController(WorkspaceService crudService) {
        super(crudService);
    }
}
