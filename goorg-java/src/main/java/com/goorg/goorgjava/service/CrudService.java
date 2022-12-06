package com.goorg.goorgjava.service;

import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.mapper.ICrudMapper;
import com.goorg.goorgjava.model.BaseEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class CrudService <E extends BaseEntity, D extends BaseEntityDto, R extends CrudRepository<E, Long>, M extends ICrudMapper<E, D>> implements ICrudService<D>{
    protected R repository;
    protected M mapper;


    @Override
    @Transactional
    public D save(D d) {
        E entity = this.mapper.toEntity(d);
        return this.mapper.toDto(this.repository.save(entity));
    }

    @Override
    @Transactional
    public Iterable<D> saveAll(List<D> dList) {
        List<E> entities = new ArrayList<>();
        List<D> dtoSaveds = new ArrayList<>();

        for (D dto: dList) entities.add(this.mapper.toEntity(dto));
        entities = (List<E>) this.repository.saveAll(entities);
        for (E entity: entities) dtoSaveds.add(this.mapper.toDto(entity));

        return  dtoSaveds;
    }

    @Override
    public Iterable<D> getAll() {
        List<E> eList = (List<E>) this.repository.findAll();
        List<D> dtoFound = new ArrayList<>();

        for (E entity: eList) dtoFound.add(this.mapper.toDto(entity));

        return dtoFound;
    }

    @Override
    public Optional<D> getById(Long id) {
        Optional<E> entity = this.repository.findById(id);
        return entity.map(e -> this.mapper.toDto(e));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        E deletedE = findByIdOrThrowBadRequestException(id);
        this.repository.delete(deletedE);
    }

    @Override
    @Transactional
    public void updateAll(List<D> dList) {
        for (D dto: dList){
            this.update(dto);
        }
    }

    @Override
    @Transactional
    public void update(D d) {
        E entity = this.mapper.toEntity(d);
        E oldE = this.findByIdOrThrowBadRequestException(entity.getId());
        this.updateData(oldE, entity);
        this.save(this.mapper.toDto(oldE));
    }



    protected E findByIdOrThrowBadRequestException(long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Entity not Found"));
    }

    protected abstract void updateData(E oldE, E newE);
}
