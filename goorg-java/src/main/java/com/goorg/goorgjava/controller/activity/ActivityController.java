package com.goorg.goorgjava.controller.activity;

import com.goorg.goorgjava.controller.CrudController;
import com.goorg.goorgjava.dto.activity.ActivityDto;
import com.goorg.goorgjava.enums.Phase;
import com.goorg.goorgjava.service.ActivityService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/activity")
public class ActivityController extends CrudController<ActivityDto, ActivityService> {

    public ActivityController(ActivityService crudService) {
        super(crudService);
    }

    @PutMapping(path = "/priorityTag")
    public @ResponseBody ActivityDto updatePriorityTag(@RequestParam(name = "idPriorityTag") Long idPriorityTag, @RequestParam(name = "idActivity") Long idActivity){
        return this.crudService.changePriorityTag(idPriorityTag, idActivity);
    }

    @PutMapping(path = "/phase")
    public @ResponseBody ActivityDto updatePhase(@RequestParam(name = "phase")Phase phase, @RequestParam(name = "idActivity") Long idActivity){
        return this.crudService.changePhase(phase, idActivity);
    }
}
