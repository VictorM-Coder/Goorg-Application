package com.goorg.goorgjava.service;

import com.goorg.goorgjava.dto.activity.ActivityDto;
import com.goorg.goorgjava.enums.Phase;
import com.goorg.goorgjava.exception.BadRequestException;
import com.goorg.goorgjava.mapper.ActivityMapper;
import com.goorg.goorgjava.model.activity.Activity;
import com.goorg.goorgjava.model.activity.PriorityTag;
import com.goorg.goorgjava.repositories.ActivityRepository;
import com.goorg.goorgjava.repositories.PriorityTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityService extends CrudService<Activity, ActivityDto, ActivityRepository, ActivityMapper> {
    @Autowired
    private PriorityTagRepository priorityTagRepository;

    public ActivityService(ActivityRepository repository, ActivityMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected void updateData(Activity oldActivity, Activity newActivity){
        oldActivity.setTitle(newActivity.getTitle());
        oldActivity.setDescription(newActivity.getDescription());
        oldActivity.setPhase(newActivity.getPhase());
        oldActivity.setStartDate(newActivity.getStartDate());
        oldActivity.setEndDate(newActivity.getEndDate());
        oldActivity.setAnotations(newActivity.getAnotations());
    }

    @Transactional
    public ActivityDto changePriorityTag(Long idPriorityTag, Long idActivity){
        PriorityTag priorityTag = this.priorityTagRepository.findById(idPriorityTag)
                .orElseThrow(() -> new BadRequestException("Tag not found"));

        Activity activity = this.findByIdOrThrowBadRequestException(idActivity);

        activity.setPriorityTag(priorityTag);
        return this.save(this.mapper.toDto(activity));
    }

    @Transactional
    public ActivityDto changePhase(Phase phase, Long idActivity){
        Activity activity = this.findByIdOrThrowBadRequestException(idActivity);
        activity.setPhase(phase);
        return this.save(this.mapper.toDto(activity));
    }
}
