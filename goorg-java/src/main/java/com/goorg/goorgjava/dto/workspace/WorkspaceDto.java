package com.goorg.goorgjava.dto.workspace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.model.activity.Activity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class WorkspaceDto extends BaseEntityDto {
    @Column(unique = true)
    private String name;

    private String description;

    @JsonIgnore
    private List<Activity> activities;

    public WorkspaceDto() {
        this.activities = new ArrayList<>();
    }

    public int getCountActivities(){
        return this.activities.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkspaceDto that = (WorkspaceDto) o;
        return Objects.equals(this.getId(), that.getId()) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(activities, that.activities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, activities);
    }
}
