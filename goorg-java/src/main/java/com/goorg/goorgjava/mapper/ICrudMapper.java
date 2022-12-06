package com.goorg.goorgjava.mapper;

import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.model.BaseEntity;

public interface ICrudMapper<E extends BaseEntity, D extends BaseEntityDto> {
    E toEntity(D d);
    D toDto(E e);
}
