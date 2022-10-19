package com.goorg.goorgjava.service;

import com.goorg.goorgjava.model.atividade.Atividade;
import com.goorg.goorgjava.repositories.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService implements ServiceInterface<Atividade,Long> {
    @Autowired
    private AtividadeRepository atividadeRepository;

    @Override
    public void save(Atividade atividade){
        this.atividadeRepository.save(atividade);
    }

    @Override
    public void saveAll(List<Atividade> atividades){
        this.atividadeRepository.saveAll(atividades);
    }

    @Override
    public Iterable<Atividade> getAll(){
        return this.atividadeRepository.findAll();
    }

    @Override
    public Optional<Atividade> getById(Long id) {
        return this.atividadeRepository.findById(id);
    }
}
