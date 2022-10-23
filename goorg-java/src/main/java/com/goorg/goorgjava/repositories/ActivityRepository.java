package com.goorg.goorgjava.repositories;

import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.model.workspace.Workspace;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends PagingAndSortingRepository<Activity, Long> {
    Iterable<Activity> findActivitiesByWorkspaceName(String name);
}
