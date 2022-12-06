package com.goorg.goorgjava.dto.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.model.atividade.Activity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto extends BaseEntityDto {
    @NotNull
    private String title;

    @NotNull
    private boolean complete;

    @NotNull(message = "atividade é obrigatória")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Activity activity;
    
}
