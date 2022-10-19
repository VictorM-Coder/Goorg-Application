package com.goorg.goorgjava.repositories;

import com.goorg.goorgjava.model.atividade.Atividade;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends PagingAndSortingRepository<Atividade, Long> {
}
