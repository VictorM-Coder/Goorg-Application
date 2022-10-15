package com.goorg.goorgjava.repositories;

import com.goorg.goorgjava.model.workspace.Workspace;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WorkspaceRepository extends PagingAndSortingRepository<Workspace, Long> {
}
