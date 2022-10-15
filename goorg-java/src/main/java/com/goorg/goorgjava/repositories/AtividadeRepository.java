package com.goorg.goorgjava.repositories;

import com.goorg.goorgjava.model.atividade.Atividade;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AtividadeRepository extends PagingAndSortingRepository<Atividade, Long> {
}
