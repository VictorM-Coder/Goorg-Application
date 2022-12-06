package com.goorg.goorgjava.dto.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goorg.goorgjava.dto.BaseEntityDto;
import com.goorg.goorgjava.model.activity.Activity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDto taskDto = (TaskDto) o;
        return Objects.equals(this.getId(), taskDto.getId()) && complete == taskDto.complete && Objects.equals(title, taskDto.title) && Objects.equals(activity, taskDto.activity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, complete, activity);
    }
}
