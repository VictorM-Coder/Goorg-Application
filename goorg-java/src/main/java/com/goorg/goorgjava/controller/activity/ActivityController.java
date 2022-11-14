package com.goorg.goorgjava.controller.activity;

import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @PostMapping
    public @ResponseBody Activity postActivity(@Valid @RequestBody Activity activity){
        return this.activityService.save(activity);
    }

    @PostMapping(path = "/all")
    public @ResponseBody Iterable<Activity> postActivities(@Valid @RequestBody List<Activity> activities){
        return this.activityService.saveAll(activities);
    }

    @PutMapping()
    public @ResponseBody Activity updateActivity(@Valid @RequestBody Activity activity){
        return this.activityService.update(activity);
    }

    @PutMapping(path = "/priorityTag")
    public @ResponseBody Activity updatePriorityTag(@RequestParam(name = "idPriorityTag") Long idPriorityTag, @RequestParam(name = "idActivity") Long idActivity){
        return this.activityService.changePriorityTag(idPriorityTag, idActivity);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Activity> getAllActivities(){
        return this.activityService.getAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Activity> getActivityById(@PathVariable Long id){
        return this.activityService.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody void deleteActivity(@PathVariable Long id){
        this.activityService.delete(id);
    }

}
