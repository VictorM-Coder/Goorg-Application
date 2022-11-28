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
}
