package com.goorg.goorgjava.mapper;

import com.goorg.goorgjava.dto.activity.ActivityDto;
import com.goorg.goorgjava.model.activity.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper implements ICrudMapper<Activity, ActivityDto>{
    private static final PriorityTagMapper priorityTagMapper = new PriorityTagMapper();
    private static final WorkspaceMapper workspaceMapper = new WorkspaceMapper();

    @Override
    public Activity toEntity(ActivityDto activityDto) {
        Activity activity = new Activity();
        activity.setId(activityDto.getId());
        activity.setTitle(activityDto.getTitle());
        activity.setPhase(activityDto.getPhase());
        activity.setAnotations(activityDto.getAnotations());
        activity.setStartDate(activityDto.getStartDate());
        activity.setPriorityTag(priorityTagMapper.toEntity(activityDto.getPriorityTag()));
        activity.setEndDate(activityDto.getEndDate());
        activity.setDescription(activityDto.getDescription());
        activity.setTasks(activityDto.getTasks());
        activity.setWorkspace(workspaceMapper.toEntity(activityDto.getWorkspace()));

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
        activityDto.setPriorityTag(priorityTagMapper.toDto(activity.getPriorityTag()));
        activityDto.setEndDate(activity.getEndDate());
        activityDto.setDescription(activity.getDescription());
        activityDto.setTasks(activity.getTasks());
        activityDto.setWorkspace(workspaceMapper.toDto(activity.getWorkspace()));

        return activityDto;
    }
}
