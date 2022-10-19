package com.goorg.goorgjava.repositories;

import com.goorg.goorgjava.model.atividade.TagDePrioridade;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDePrioridadeRepository extends PagingAndSortingRepository<TagDePrioridade, Long> {
}
