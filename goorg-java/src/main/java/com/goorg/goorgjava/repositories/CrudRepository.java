package com.goorg.goorgjava.repositories;

import com.goorg.goorgjava.model.BaseEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudRepository extends PagingAndSortingRepository<BaseEntity, Long> {
}
