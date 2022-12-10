package com.goorg.goorgjava.controller.activity;

import com.goorg.goorgjava.controller.CrudController;
import com.goorg.goorgjava.dto.activity.PriorityTagDto;
import com.goorg.goorgjava.service.PriorityTagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping(path = "/priorityTag")
public class PriorityTagController extends CrudController<PriorityTagDto, PriorityTagService> {

    public PriorityTagController(PriorityTagService crudService) {
        super(crudService);
    }
}
