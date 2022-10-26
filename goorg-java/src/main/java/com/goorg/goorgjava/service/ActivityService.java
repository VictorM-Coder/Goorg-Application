package com.goorg.goorgjava.service;

import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService implements ServiceInterface<Activity,Long> {
    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public void save(Activity activity){
        this.activityRepository.save(activity);
    }

    @Override
    public void saveAll(List<Activity> activities){
        this.activityRepository.saveAll(activities);
    }

    @Override
    public Iterable<Activity> getAll(){
        return this.activityRepository.findAll();
    }

    @Override
    public Optional<Activity> getById(Long id) {
        return this.activityRepository.findById(id);
    }

    public Iterable<Activity> getActivityByWorkspaceName(String name){
        return this.activityRepository.findActivitiesByWorkspaceName(name);
    }
}
