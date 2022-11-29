package com.goorg.goorgjava.service;

import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.model.BaseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ICrudService<D extends BaseEntityDto> {
    D save(D e);

    Iterable<D> saveAll(List<D> eList);

    Iterable<D> getAll();

    Optional<D> getById(Long id);

    void update(D e);

    void updateAll(List<D> dList);

    void delete(Long id);
}
