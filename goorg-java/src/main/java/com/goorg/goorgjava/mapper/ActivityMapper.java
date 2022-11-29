package com.goorg.goorgjava.mapper;

import com.goorg.goorgjava.dto.activity.ActivityDto;
import com.goorg.goorgjava.model.atividade.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper implements ICrudMapper<Activity, ActivityDto>{
    @Override
    public Activity toEntity(ActivityDto activityDto) {
        Activity activity = new Activity();
        activity.setId(activityDto.getId());
        activity.setTitle(activityDto.getTitle());
        activity.setPhase(activityDto.getPhase());
        activity.setAnotations(activityDto.getAnotations());
        activity.setStartDate(activityDto.getStartDate());
        activity.setPriorityTag(activityDto.getPriorityTag());
        activity.setEndDate(activityDto.getEndDate());
        activity.setDescription(activityDto.getDescription());
        activity.setTasks(activityDto.getTasks());
        activity.setWorkspace(activityDto.getWorkspace());

        return activity;
    }

    @Override
    public ActivityDto toDto(Activity activity) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getId());
        activityDto.setTitle(activity.getTitle());
        activityDto.setPhase(activity.getPhase());
        activityDto.setAnotations(activity.getAnotations());
        activityDto.setStartDate(activity.getStartDate());
        activityDto.setPriorityTag(activity.getPriorityTag());
        activityDto.setEndDate(activity.getEndDate());
        activityDto.setDescription(activity.getDescription());
        activityDto.setTasks(activity.getTasks());
        activityDto.setWorkspace(activity.getWorkspace());

        return activityDto;
    }
}
