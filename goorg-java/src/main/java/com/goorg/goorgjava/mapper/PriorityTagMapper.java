package com.goorg.goorgjava.mapper;

import com.goorg.goorgjava.dto.activity.PriorityTagDto;
import com.goorg.goorgjava.model.activity.PriorityTag;
import org.springframework.stereotype.Component;

@Component
public class PriorityTagMapper implements ICrudMapper<PriorityTag, PriorityTagDto> {
    @Override
    public PriorityTag toEntity(PriorityTagDto priorityTagDto) {
        PriorityTag priorityTag = new PriorityTag();
        priorityTag.setName(priorityTagDto.getName());
        priorityTag.setId(priorityTagDto.getId());

        return priorityTag;
    }

    @Override
    public PriorityTagDto toDto(PriorityTag priorityTag) {
        PriorityTagDto priorityTagDto = new PriorityTagDto();
        priorityTagDto.setName(priorityTag.getName());
        priorityTagDto.setId(priorityTag.getId());

        return priorityTagDto;
    }
}
