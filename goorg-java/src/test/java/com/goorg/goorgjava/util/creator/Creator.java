package com.goorg.goorgjava.util.creator;

import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.model.BaseEntity;

import java.util.List;

public interface Creator<E extends BaseEntity> {
    E createValidItem();
    List<E> createValidItemsList();
}
