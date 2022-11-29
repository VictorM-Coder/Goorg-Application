package com.goorg.goorgjava.service;

import com.goorg.goorgjava.dto.activity.PriorityTagDto;
import com.goorg.goorgjava.mapper.PriorityTagMapper;
import com.goorg.goorgjava.model.atividade.PriorityTag;
import com.goorg.goorgjava.repositories.PriorityTagRepository;
import org.springframework.stereotype.Service;

@Service
public class PriorityTagService extends CrudService<PriorityTag, PriorityTagDto, PriorityTagRepository, PriorityTagMapper> {
    public PriorityTagService(PriorityTagRepository repository, PriorityTagMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected void updateData(PriorityTag oldTag, PriorityTag newTag){
        oldTag.setName(newTag.getName());
    }
}
