package com.goorg.goorgjava.controller;

import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.service.ICrudService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CrudController<D extends BaseEntityDto, S extends ICrudService<D>> implements ICrudController<D>{
    protected S crudService;


    @Override
    @PostMapping
    public @ResponseBody D post(@RequestBody @Valid D baseEntityDto) {
        return this.crudService.save(baseEntityDto);
    }

    @Override
    @PostMapping(path = "/all")
    public @ResponseBody Iterable<D> postAll(@RequestBody @Valid List<D> list) {
        return this.crudService.saveAll(list);
    }

    @Override
    @PutMapping
    public @ResponseBody void put(@RequestBody @Valid D baseEntityDto) {
        this.crudService.update(baseEntityDto);
    }

    @Override
    @PutMapping(path = "/all")
    public @ResponseBody void putAll(@RequestBody @Valid List<D> list) {
        this.crudService.updateAll(list);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public @ResponseBody void delete(@PathVariable Long id) {
        this.crudService.delete(id);
    }

    @Override
    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<D> getById(@PathVariable Long id) {
        return this.crudService.getById(id);
    }

    @Override
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<D> getAll() {
        return this.crudService.getAll();
    }
}
