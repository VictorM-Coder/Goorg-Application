package com.goorg.goorgjava.repositories;

import com.goorg.goorgjava.model.activity.PriorityTag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityTagRepository extends PagingAndSortingRepository<PriorityTag, Long> {
}
