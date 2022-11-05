package com.goorg.goorgjava.controller.activity;

import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
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
    public @ResponseBody void postActivity(@Valid @RequestBody Activity activity){
        this.activityService.save(activity);
    }

    @PostMapping(path = "/all")
    public @ResponseBody void postActivities(@Valid @RequestBody List<Activity> activities){
        this.activityService.saveAll(activities);
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody Activity updateActivity(@PathVariable Long id, @Valid @RequestBody Activity activity){
        return this.activityService.update(id, activity);
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
    public @ResponseBody Activity deleteActivity(@PathVariable Long id){
        return this.activityService.delete(id);
    }

}
