package com.goorg.goorgjava.repositories;

import com.goorg.goorgjava.model.atividade.Task;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
}
