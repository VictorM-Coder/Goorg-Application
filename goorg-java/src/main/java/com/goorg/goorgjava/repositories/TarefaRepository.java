package com.goorg.goorgjava.repositories;

import com.goorg.goorgjava.model.atividade.Tarefa;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TarefaRepository extends PagingAndSortingRepository<Tarefa, Long> {
}
