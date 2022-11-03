package com.goorg.goorgjava.controller.activity;

import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.model.atividade.Task;
import com.goorg.goorgjava.service.ActivityService;
import com.goorg.goorgjava.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(path = "/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private ActivityService activityService;

    @PostMapping(path = "/{activityId}")
    public @ResponseBody void postTask(@Valid @RequestBody Task task, @PathVariable Long activityId){
        Optional<Activity> activity = activityService.getById(activityId);
        task.setActivity(activity.orElseThrow( () -> new BadRequestException("Activity not found")));
        this.taskService.save(task);
    }

    @PutMapping(path = "/update/{activiyId}/{id}")
    public @ResponseBody Task updateTask(@PathVariable Long id, @PathVariable Long activiyId, @Valid @RequestBody Task task){
        Optional<Activity> activity = this.activityService.getById(activiyId);
        task.setActivity(activity.orElseThrow( () -> new BadRequestException("Activity not found")));
        return this.taskService.update(id, task);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Task> getAllTasks(){
        return this.taskService.getAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Task> getTaskById(@PathVariable Long id){
        return this.taskService.getById(id);
    }
}
