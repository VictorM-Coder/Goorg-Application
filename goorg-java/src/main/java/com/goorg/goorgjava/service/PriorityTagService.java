package com.goorg.goorgjava.service;

import com.goorg.goorgjava.model.atividade.PriorityTag;
import com.goorg.goorgjava.repositories.PriorityTagRepository;
import org.springframework.stereotype.Service;

@Service
public class PriorityTagService extends CrudService<PriorityTag, PriorityTagRepository> {
    public PriorityTagService(PriorityTagRepository repository) {
        super(repository);
    }

    @Override
    protected void updateData(PriorityTag oldTag, PriorityTag newTag){
        oldTag.setName(newTag.getName());
    }
}
