package com.goorg.goorgjava.controller.activity;

import com.goorg.goorgjava.controller.CrudController;
import com.goorg.goorgjava.dto.activity.TaskDto;
import com.goorg.goorgjava.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping(path = "/task")
public class TaskController extends CrudController<TaskDto, TaskService> {

    public TaskController(TaskService crudService) {
        super(crudService);
    }
}
