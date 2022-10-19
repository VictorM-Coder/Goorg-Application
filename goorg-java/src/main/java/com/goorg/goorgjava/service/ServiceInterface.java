package com.goorg.goorgjava.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ServiceInterface<E, K> {
    void save(E e);

    void saveAll(List<E> eList);

    Iterable<E> getAll();

    Optional<E> getById(K k);
}
