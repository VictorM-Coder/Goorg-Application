package com.goorg.goorgjava.controller;

import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.model.BaseEntity;
import com.goorg.goorgjava.service.ICrudService;

import java.util.List;
import java.util.Optional;

public interface ICrudController<D extends BaseEntityDto> {
     D post(D d);
     Iterable<D> postAll(List<D> dList);
     void put(D d);
     void putAll(List<D> dList);
     void delete(Long id);
     Optional<D> getById(Long id);
     Iterable<D> getAll();
}
