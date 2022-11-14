package com.goorg.goorgjava.controller.activity;

import com.goorg.goorgjava.model.atividade.PriorityTag;
import com.goorg.goorgjava.service.PriorityTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/priorityTag")
public class PriorityTagController {
    @Autowired
    private PriorityTagService priorityTagService;

    @PostMapping
    public @ResponseBody PriorityTag postTag(@Valid @RequestBody PriorityTag tag){
        return this.priorityTagService.save(tag);
    }

    @PostMapping(path = "/all")
    public @ResponseBody Iterable<PriorityTag> postTags(@Valid @RequestBody List<PriorityTag> tags){
       return  this.priorityTagService.saveAll(tags);
    }

    @PutMapping()
    public @ResponseBody PriorityTag updateTask(@Valid @RequestBody PriorityTag tag){
        return this.priorityTagService.update(tag);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<PriorityTag> getAllTasks(){
        return this.priorityTagService.getAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<PriorityTag> getTaskById(@PathVariable Long id){
        return this.priorityTagService.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody void deletePriorityTag(@PathVariable Long id){
        this.priorityTagService.delete(id);
    }
}
