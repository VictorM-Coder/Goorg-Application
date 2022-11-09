package com.goorg.goorgjava.service;

import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.model.atividade.PriorityTag;
import com.goorg.goorgjava.repositories.PriorityTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PriorityTagService implements ServiceInterface<PriorityTag>{
    @Autowired
    private PriorityTagRepository priorityTagRepository;

    @Override
    @Transactional
    public PriorityTag save(PriorityTag priorityTag){
        return this.priorityTagRepository.save(priorityTag);
    }

    @Override
    @Transactional
    public Iterable<PriorityTag> saveAll(List<PriorityTag> tags){
        return this.priorityTagRepository.saveAll(tags);
    }

    @Override
    public Iterable<PriorityTag> getAll(){
        return this.priorityTagRepository.findAll();
    }

    @Override
    public Optional<PriorityTag> getById(Long id) {
        return this.priorityTagRepository.findById(id);
    }

    @Override
    public PriorityTag update(Long id, PriorityTag priorityTag) {
        PriorityTag oldTag = this.findByIdOrThrowBadRequestException(id);
        this.updateData(oldTag, priorityTag);
        return this.save(oldTag);
    }

    @Override
    public void delete(Long id) {
        PriorityTag deletedPriorityTag = findByIdOrThrowBadRequestException(id);
        this.priorityTagRepository.delete(deletedPriorityTag);
    }

    private void updateData(PriorityTag oldTag, PriorityTag newTag){
        oldTag.setName(newTag.getName());
    }

    public PriorityTag findByIdOrThrowBadRequestException(long id) {
        return priorityTagRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Tag not Found"));
    }

    @Override
    public PriorityTag delete(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
}
