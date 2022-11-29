package com.goorg.goorgjava.controller.activity;

import com.goorg.goorgjava.controller.CrudController;
import com.goorg.goorgjava.dto.activity.PriorityTagDto;
import com.goorg.goorgjava.dto.activity.TaskDto;
import com.goorg.goorgjava.model.atividade.PriorityTag;
import com.goorg.goorgjava.service.PriorityTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
@RequestMapping(path = "/priorityTag")
public class PriorityTagController extends CrudController<PriorityTagDto, PriorityTagService> {

    public PriorityTagController(PriorityTagService crudService) {
        super(crudService);
    }
}
