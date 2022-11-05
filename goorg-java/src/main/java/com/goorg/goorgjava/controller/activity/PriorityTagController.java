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
    public @ResponseBody void postTag(@Valid @RequestBody PriorityTag tag){
        this.priorityTagService.save(tag);
    }

    @PostMapping(path = "/all")
    public @ResponseBody void postTags(@Valid @RequestBody List<PriorityTag> tags){
        this.priorityTagService.saveAll(tags);
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody PriorityTag updateTask(@PathVariable Long id, @Valid @RequestBody PriorityTag tag){
        return this.priorityTagService.update(id, tag);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<PriorityTag> getAllTasks(){
        return this.priorityTagService.getAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<PriorityTag> getTaskById(@PathVariable Long id){
        return this.priorityTagService.getById(id);
    }
}
