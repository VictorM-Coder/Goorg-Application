package com.goorg.goorgjava.service;

import com.goorg.goorgjava.model.atividade.Atividade;

import java.util.List;
import java.util.Optional;

public interface Service<E, K> {
    void save(E e);

    void saveAll(List<E> eList);

    Iterable<E> getAll();

    Optional<E> getById(K k);
}
