package com.goorg.goorgjava.dto.workspace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.model.atividade.Activity;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public class WorkspaceDto extends BaseEntityDto {
    @UniqueElements
    private String name;

    private String description;

    @JsonIgnore
    private List<Activity> activities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
