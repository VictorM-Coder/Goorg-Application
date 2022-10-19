package com.goorg.goorgjava.controller.atividade;

import com.goorg.goorgjava.model.atividade.Atividade;
import com.goorg.goorgjava.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/atividade")
public class AtividadeController {
    @Autowired
    private AtividadeService atividadeService;

    @PostMapping
    public @ResponseBody void postAtividade(@Valid @RequestBody Atividade atividade){
        this.atividadeService.save(atividade);
    }

    @PostMapping(path = "/all")
    public @ResponseBody void postAtividades(@Valid @RequestBody List<Atividade> atividades){
        this.atividadeService.saveAll(atividades);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Atividade> getAllAtividades(){
        return this.atividadeService.getAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Atividade> getAtividadePorId(@PathVariable Long id){
        return this.atividadeService.getById(id);
    }
}
