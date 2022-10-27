package com.goorg.goorgjava.service;

import com.goorg.goorgjava.model.atividade.Activity;
import com.goorg.goorgjava.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component("ActivityService")
public class ActivityService implements ServiceInterface<Activity> {
    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Activity save(Activity activity){
        return this.activityRepository.save(activity);
    }

    @Override
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
}
