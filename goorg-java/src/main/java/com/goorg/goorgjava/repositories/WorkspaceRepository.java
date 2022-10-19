package com.goorg.goorgjava.repositories;

import com.goorg.goorgjava.model.workspace.Workspace;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends PagingAndSortingRepository<Workspace, Long> {
}
