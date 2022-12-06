package com.goorg.goorgjava.service;

import com.goorg.goorgjava.enums.Phase;
import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.model.atividade.PriorityTag;
import com.goorg.goorgjava.repositories.ActivityRepository;
import com.goorg.goorgjava.repositories.PriorityTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Component("ActivityService")
public class ActivityService implements ServiceInterface<Activity> {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private PriorityTagRepository priorityTagRepository;

    @Override
    @Transactional
    public Activity save(Activity activity){
        return this.activityRepository.save(activity);
    }

    @Override
    @Transactional
    public Iterable<Activity> saveAll(List<Activity> activities){
        return this.activityRepository.saveAll(activities);
    }

    @Override
    public Iterable<Activity> getAll(){
        return this.activityRepository.findAll();
    }

    @Override
    public Optional<Activity> getById(Long id) {
        return this.activityRepository.findById(id);
    }

    @Override
    public Activity update(Activity activity) {
        Activity oldActivity = this.findByIdOrThrowBadRequestException(activity.getId());
        this.updateData(oldActivity, activity);
        return this.save(oldActivity);
    }

    private void updateData(Activity oldActivity, Activity newActivity){
        oldActivity.setTitle(newActivity.getTitle());
        oldActivity.setDescription(newActivity.getDescription());
        oldActivity.setPhase(newActivity.getPhase());
        oldActivity.setStartDate(newActivity.getStartDate());
        oldActivity.setEndDate(newActivity.getEndDate());
        oldActivity.setAnotations(newActivity.getAnotations());
    }

    public Activity findByIdOrThrowBadRequestException(long id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Activity not Found"));
    }

    @Override
    public void delete(Long id) {
        Activity deletedActivity = findByIdOrThrowBadRequestException(id);
        this.activityRepository.delete(deletedActivity);
    }

    @Transactional
    public Activity changePriorityTag(Long idPriorityTag, Long idActivity){
        PriorityTag priorityTag = this.priorityTagRepository.findById(idPriorityTag)
                .orElseThrow(() -> new BadRequestException("Tag not found"));

        Activity activity = this.findByIdOrThrowBadRequestException(idActivity);

        activity.setPriorityTag(priorityTag);
        return this.save(activity);
    }

    @Transactional
    public Activity changePhase(Phase phase, Long idActivity){
        Activity activity = this.findByIdOrThrowBadRequestException(idActivity);
        activity.setPhase(phase);
        return this.activityRepository.save(activity);
    }
}
