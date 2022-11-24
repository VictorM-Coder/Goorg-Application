package com.goorg.goorgjava.service;

import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.model.BaseEntity;
import com.goorg.goorgjava.model.atividade.Activity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class CrudService <E extends BaseEntity, R extends CrudRepository<E, Long>> implements ICrudService<E>{
    protected R repository;


    @Override
    public E save(E e) {
        return this.repository.save(e);
    }

    @Override
    public Iterable<E> saveAll(List<E> eList) {
        return this.repository.saveAll(eList);
    }

    @Override
    public Iterable<E> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<E> getById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        E deletedE = findByIdOrThrowBadRequestException(id);
        this.repository.delete(deletedE);
    }

    @Override
    public E update(E e) {
        E oldE = this.findByIdOrThrowBadRequestException(e.getId());
        this.updateData(oldE, e);
        return this.save(oldE);
    }

    @Override
    public E findByIdOrThrowBadRequestException(long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Entity not Found"));
    }

    protected abstract void updateData(E oldE, E newE);
}
