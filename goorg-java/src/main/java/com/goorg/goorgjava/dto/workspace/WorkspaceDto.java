package com.goorg.goorgjava.dto.workspace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.model.atividade.Activity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

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
}
