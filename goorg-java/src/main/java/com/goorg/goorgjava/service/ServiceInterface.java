package com.goorg.goorgjava.service;

import com.goorg.goorgjava.model.atividade.Activity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ServiceInterface<E> {
    E save(E e);

    Iterable<E> saveAll(List<E> eList);

    Iterable<E> getAll();

    Optional<E> getById(Long id);
}
