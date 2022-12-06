package com.goorg.goorgjava.repositories;

import com.goorg.goorgjava.model.atividade.Activity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends PagingAndSortingRepository<Activity, Long> {

}
