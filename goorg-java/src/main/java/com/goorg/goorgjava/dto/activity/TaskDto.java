package com.goorg.goorgjava.dto.activity;

import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.model.atividade.Activity;

import javax.validation.constraints.NotNull;

public class TaskDto extends BaseEntityDto {
    @NotNull
    private String title;

    @NotNull
    private boolean complete;

    private Activity activity;
}
